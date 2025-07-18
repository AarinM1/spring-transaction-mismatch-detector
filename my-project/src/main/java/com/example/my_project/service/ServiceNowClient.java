package com.example.my_project.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceNowClient {
    private final RestTemplate restTemplate = new RestTemplate();

    public void createIncident(String transactionId, String description, Instant timestamp) {
        String url = "https://dev.servicenow.com/api/now/table/incident";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("api_user", "api_password");

        Map<String, Object> body = new HashMap<>();
        body.put("short_description", "Transaction Mismatch: "+transactionId);
        body.put("description", description+" at "+timestamp);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        restTemplate.postForEntity(url, request, String.class);
    }
    
}
