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

    public EthereumLog save(List<TopicWord> topicWords, List<EthereumLogData> logData, String txHash) {
        EthereumLog ethereumLog = new EthereumLog();
        // Try to get allLogData
        // Try to get allTopicWords
        return this.ethereumLogRepository.save(ethereumLog);
    }

    public List<EthereumLog> getByTopic(String topic) {
        return new ArrayList<>();
    }
}
