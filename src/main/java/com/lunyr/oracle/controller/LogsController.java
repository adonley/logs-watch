package com.lunyr.oracle.controller;


import com.lunyr.oracle.model.entity.EthereumLog;
import com.lunyr.oracle.service.EthereumLogService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class LogsController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final EthereumLogService ethereumLogService;

    @Autowired
    public LogsController(final EthereumLogService ethereumLogService) {
        this.ethereumLogService = ethereumLogService;
    }

    @GetMapping(path="/")
    public List<EthereumLog> getLogs(@PathVariable String topic, BindingResult bindingResult) {
        this.ethereumLogService.getById(1L);
        return this.ethereumLogService.getByTopic(topic);
    }
}
