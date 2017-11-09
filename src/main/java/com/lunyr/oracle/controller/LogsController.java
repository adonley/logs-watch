package com.lunyr.oracle.controller;


import com.lunyr.oracle.model.entity.EthereumLog;
import com.lunyr.oracle.service.EthereumLogService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LogsController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final EthereumLogService ethereumLogService;

    @Autowired
    public LogsController(final EthereumLogService ethereumLogService) {
        this.ethereumLogService = ethereumLogService;
    }

    @RequestMapping(value="/", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE )
    public List<EthereumLog> getLogs(@RequestParam String topic) {
        return this.ethereumLogService.getByTopic(topic);
    }
}
