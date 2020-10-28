package com.github.meklund.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BackendService {
    private static final Logger logger = LoggerFactory.getLogger(BackendService.class);
    private final RestTemplate restTemplate;

    @Autowired
    public BackendService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> fetchSomething(String backendUrl) {
        ResponseEntity<String> responseEntity = null;
        HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
        logger.info("Sending request to backend-service on url: {}",backendUrl);
        responseEntity = restTemplate.exchange(backendUrl, HttpMethod.GET, entity, String.class);
        return responseEntity;
    }
}
