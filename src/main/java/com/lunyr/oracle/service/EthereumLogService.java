package com.lunyr.oracle.service;

import com.lunyr.oracle.model.entity.EthereumLog;
import com.lunyr.oracle.model.entity.EthereumLogData;
import com.lunyr.oracle.model.entity.TopicWord;
import com.lunyr.oracle.repository.EthereumLogDataRepository;
import com.lunyr.oracle.repository.EthereumLogRepository;
import com.lunyr.oracle.repository.TopicWordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class EthereumLogService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final EthereumLogRepository ethereumLogRepository;
    private final EthereumLogDataRepository ethereumLogDataRepository;
    private final TopicWordRepository topicWordRepository;

    @Autowired
    public EthereumLogService(
            final EthereumLogRepository ethereumLogRepository,
            final TopicWordRepository topicWordRepository,
            final EthereumLogDataRepository ethereumLogDataRepository
            ) {
        this.ethereumLogRepository = ethereumLogRepository;
        this.topicWordRepository = topicWordRepository;
        this.ethereumLogDataRepository = ethereumLogDataRepository;
    }

    public EthereumLog save(List<String> topicWords, List<String> logData, String txHash)
            throws InterruptedException, ExecutionException{
        EthereumLog ethereumLog = new EthereumLog();

        // Try to get allLogData
        List<Future> topicWordFutures = new ArrayList<>(topicWords.size());
        List<Future> logDataFutures = new ArrayList<>(logData.size());
        List<TopicWord> topicWordResults = new ArrayList<>(topicWords.size());
        List<EthereumLogData> logDataResults = new ArrayList<>(logData.size());

        // TODO: Could cache this to make it faster
        for(String s: topicWords) topicWordFutures.add(this.topicWordRepository.findByHashAsync(s));
        for(String s: logData) logDataFutures.add(this.ethereumLogDataRepository.findByHexDataAsync(s));

        for(int i = 0; i < topicWordFutures.size(); i++) {
            Object o = topicWordFutures.get(i).get();
            if(o != null) {
                topicWordResults.add((TopicWord)o);
            } else {
                int before = findBefore(topicWords.get(i), topicWords, i);
                // Deal with duplicate topics
                if(before > -1) {
                    topicWordResults.add(topicWordResults.get(before));
                } else {
                    TopicWord t = new TopicWord();
                    t.setHash(topicWords.get(i));
                    t = this.topicWordRepository.save(t);
                    topicWordResults.add(t);
                }
            }
        }

        for(int i = 0; i < logDataFutures.size(); i++) {
            Object o = logDataFutures.get(i).get();
            if(o != null) {
                logDataResults.add((EthereumLogData) o);
            } else {
                int before = findBefore(logData.get(i), logData, i);
                // Deal with duplicate data
                if(before > -1) {
                    logDataResults.add(logDataResults.get(before));
                } else {
                    EthereumLogData d = new EthereumLogData();
                    d.setHexData(logData.get(i));
                    d = this.ethereumLogDataRepository.save(d);
                    logDataResults.add(d);
                }
            }
        }

        ethereumLog.setTxHash(txHash);
        ethereumLog.setTopicWords(topicWordResults);
        ethereumLog.setLogData(logDataResults);
        // Try to get allTopicWords
        return this.ethereumLogRepository.save(ethereumLog);
    }

    private int findBefore(String s, List<String> list, int location) {
        for(int i = 0; i < location; i++) {
            if(list.get(i).equals(s))
                return i;
        }
        return -1;
    }

    public List<EthereumLog> getByTopic(String topic) {
        return new ArrayList<>();
    }

    public EthereumLog getById(Long id) {
        return this.ethereumLogRepository.findOne(id);
    }
}
