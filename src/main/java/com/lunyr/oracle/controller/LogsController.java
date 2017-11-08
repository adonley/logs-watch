package com.lunyr.oracle.controller;


import com.lunyr.oracle.service.EthereumLogsService;
import com.lunyr.oracle.service.LogHashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LogsController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final LogHashService logHashService;
    private final EthereumLogsService ethereumLogsService;

    @Autowired
    public LogsController(
            final LogHashService logHashService,
            final EthereumLogsService ethereumLogsService
    ) {
        this.logHashService = logHashService;
        this.ethereumLogsService = ethereumLogsService;
    }

    @GetMapping(path="/")
    public ResponseEntity<String> getLogs(@PathVariable String logHash) {
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
