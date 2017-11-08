package com.lunyr.oracle.service;

import com.lunyr.oracle.model.entity.EthereumLogHash;
import com.lunyr.oracle.repository.LogHashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogHashService {
    private final LogHashRepository logHashRepository;

    @Autowired
    public LogHashService(final LogHashRepository logHashRepository) {
        this.logHashRepository = logHashRepository;
    }

    Optional<EthereumLogHash> findOneByHash(String hash) {
        return this.logHashRepository.findByLogHash(hash);
    }
}
