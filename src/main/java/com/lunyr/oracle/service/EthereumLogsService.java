package com.lunyr.oracle.service;

import com.lunyr.oracle.model.entity.EthereumLogs;
import com.lunyr.oracle.repository.EthereumLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EthereumLogsService {
    private final EthereumLogsRepository ethereumLogsRepository;

    @Autowired
    public EthereumLogsService(final EthereumLogsRepository ethereumLogsRepository) {
        this.ethereumLogsRepository = ethereumLogsRepository;
    }

    public EthereumLogs save(EthereumLogs ethereumLogs) {
        return this.ethereumLogsRepository.save(ethereumLogs);
    }
}
