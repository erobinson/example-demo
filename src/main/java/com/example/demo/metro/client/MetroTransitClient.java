package com.example.demo.metro.client;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MetroTransitClient {

    private static final String METRO_TRANSIT_BASE_URL = "https://svc.metrotransit.org/nextripv2";
    private static final Logger logger = LoggerFactory.getLogger(MetroTransitClient.class);
    private static RestTemplate restTemplate = new RestTemplate();

    public static List<MetroAgency> getAgencies() {
        String url = METRO_TRANSIT_BASE_URL + "/agencies";
        logger.info("Getting agencies from metro transit {}...", url);
        ResponseEntity<MetroAgency[]> response = restTemplate.getForEntity(url, MetroAgency[].class);
        MetroAgency[] agencies = response.getBody();
        logger.info("Successfully retrieved {} agencies from metro transit.", agencies.length);
        return Arrays.asList(agencies);
    }
    
    
    public static List<MetroRoute> getRoutes() {
        String url = METRO_TRANSIT_BASE_URL + "/routes";
        logger.info("Getting routes from metro transit {}...", url);
        ResponseEntity<MetroRoute[]> response = restTemplate.getForEntity(url, MetroRoute[].class);
        MetroRoute[] routes = response.getBody();
        logger.info("Successfully retrieved {} routes from metro transit.", routes.length);
        return Arrays.asList(routes);
    }

}
