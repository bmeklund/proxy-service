package com.github.meklund.controller;

import com.github.meklund.domain.Destination;
import com.github.meklund.service.BackendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.Map;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

    private BackendService backendService;
    private Destination destination;

    @Autowired
    public RestController(BackendService backendService, Destination destination) {
        this.backendService = backendService;
        this.destination = destination;
    }

    @GetMapping("ping")
    public String ping() {
        logger.info("Got a ping-call, returning Pong!");
        return "Pong!! " + new Date();

    }

    @GetMapping("api")
    public ResponseEntity<String> getSomething(@RequestHeader Map<String, String> headers, @RequestParam Map<String,String> requestParams) {
        logger.info("Got call, adding and forwarding");
        logger.info("Message headers: {}", headers.toString());
        logger.info("Message queryparams: {}", requestParams.toString());
        String backend = destination.getDestination(headers);
        ResponseEntity<String> result = backendService.fetchSomething(backend);
        logger.info("Got result {}", result.toString());
        logger.info("Request done");
        return result;
    }

    @GetMapping("/")
    public String catchAll() {
        logger.info("Got a call, returning");
        return "Empty!! " + new Date();

    }

}
