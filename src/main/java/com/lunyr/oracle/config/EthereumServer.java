package com.lunyr.oracle.config;

import com.lunyr.oracle.service.EthereumLogsService;
import com.lunyr.oracle.service.LogHashService;

import lombok.Getter;
import org.ethereum.core.TransactionExecutionSummary;
import org.ethereum.facade.Ethereum;
import org.ethereum.facade.EthereumFactory;
import org.ethereum.listener.EthereumListenerAdapter;

import org.ethereum.vm.DataWord;
import org.ethereum.vm.LogInfo;
import org.ethereum.crypto.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EthereumServer {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private Ethereum ethereum;
    private EthereumLogsService ethereumLogsService;
    private LogHashService logHashService;
    private List<String> interestingLogList;
    @Getter private boolean syncDone = false;

    public EthereumServer(
            final EthereumLogsService ethereumLogsService,
            final LogHashService logHashService,
            final List<String> interestingLogList
    ) {
        this.ethereumLogsService = ethereumLogsService;
        this.logHashService = logHashService;
        this.interestingLogList = interestingLogList;
    }

    public void start() {
        this.ethereum = EthereumFactory.createEthereum();
        this.ethereum.addListener(new EthereumLogListener(ethereum, interestingLogList));
    }

    public Ethereum getEthereum() {
        return this.ethereum;
    }

    private class EthereumLogListener extends EthereumListenerAdapter {
        private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

        private final Ethereum ethereum;
        private List<String> interestingLogs;

        public EthereumLogListener(
                Ethereum ethereum,
                List<String> interestingLogs
        ) {
            this.ethereum = ethereum;

            // Was having an issue with the stream version
            this.interestingLogs = new ArrayList<>(interestingLogs.size());
            for(String log: interestingLogs) {
                StringBuilder sb = new StringBuilder();
                byte[] ba = HashUtil.sha3(log.getBytes());
                for(byte b: ba) sb.append(String.format("%02x", b));
                this.interestingLogs.add(sb.toString());
            }
            String logsConcat = this.interestingLogs.stream().collect(Collectors.joining(","));
            LOGGER.info("Interesting logs: " + logsConcat);
        }

        @Override
        public void onSyncDone(SyncState state) {
            syncDone = true;
        }

        @Override
        public void onTransactionExecuted(TransactionExecutionSummary summary) {
            super.onTransactionExecuted(summary);
            List<LogInfo> logsList = summary.getLogs();
            for(LogInfo logInfo: logsList) {
                List<String> topicWords = logInfo.getTopics()
                        .stream()
                        .map(DataWord::getData)
                        .map(ba -> {
                            StringBuilder sb = new StringBuilder();
                            for(byte b: ba) sb.append(String.format("%02x", b));
                            return sb.toString();
                        }).collect(Collectors.toList());
                String logData = Stream.of(logInfo.getData()).map(ba -> {
                    StringBuilder sb = new StringBuilder();
                    for(byte b: ba) sb.append(String.format("%02x", b));
                    return sb.toString();
                }).collect(Collectors.joining());
                List<String> data32Bytes = Arrays.asList(logData.split("(?<=\\G.{32})"));
            }
        }
    }
}
