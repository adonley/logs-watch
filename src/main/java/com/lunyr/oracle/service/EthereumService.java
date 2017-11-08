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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;

@Service
public class EthereumService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final EthereumServer ethereumServer;
    private final EthereumLogService ethereumLogService;

    @Autowired
    public EthereumService(final EthereumLogService ethereumLogService) {
        this.ethereumLogService = ethereumLogService;

        // I know... close those eyes.
        List<List<String>> tempList = new ArrayList<>();
        try {
            ApplicationContext appContext = new ClassPathXmlApplicationContext();
            Resource resource = appContext.getResource("classpath:/listen-logs.json");
            File zipsFile = resource.getFile();
            StringBuilder jsonString = new StringBuilder();
            try (Scanner scanner = new Scanner(zipsFile)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    jsonString.append(line).append("\n");
                }
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("Could not scan the listen-logs.json file: " + e.getLocalizedMessage());
            }
            String [][] taa =  new ObjectMapper().readValue(jsonString.toString(), String[][].class);
            for(String[] ta: taa) {
                List<String> l = new ArrayList<>();
                for(String t: ta) {
                    l.add(t.trim());
                }
                tempList.add(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Could not read the json file 'listen-logs.json' error: " + e.toString());
        }
        this.ethereumServer = new EthereumServer(ethereumLogService, tempList);
        Executors.newSingleThreadExecutor().submit(ethereumServer::start);
    }
}
