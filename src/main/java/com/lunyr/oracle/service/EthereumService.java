package com.lunyr.oracle.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunyr.oracle.config.EthereumServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class EthereumService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final EthereumServer ethereumServer;
    private final EthereumLogsService ethereumLogsService;
    private final LogHashService logHashService;

    @Autowired
    public EthereumService(
            final EthereumLogsService ethereumLogsService,
            final LogHashService logHashService
    ) {
        this.ethereumLogsService = ethereumLogsService;
        this.logHashService = logHashService;

        // I know... close those eyes.
        List<String> tempList = null;
        try {
            ApplicationContext appContext = new ClassPathXmlApplicationContext();
            Resource resource = appContext.getResource("classpath:/listen-logs.json");
            File zipsFile = resource.getFile();
            StringBuilder zipsString = new StringBuilder();
            try (Scanner scanner = new Scanner(zipsFile)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    zipsString.append(line).append("\n");
                }
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("Could not scan the listen-logs.json file: " + e.getLocalizedMessage());
            }
            ObjectMapper objectMapper = new ObjectMapper();
            tempList = Arrays.stream(objectMapper.readValue(zipsString.toString(), String[].class))
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Could not read the json file 'listen-logs.json' error: " + e.toString());
        }
        this.ethereumServer = new EthereumServer(ethereumLogsService, logHashService, tempList);
        Executors.newSingleThreadExecutor().submit(ethereumServer::start);
    }
}
