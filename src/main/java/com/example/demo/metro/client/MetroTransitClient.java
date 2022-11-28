package com.example.demo.metro.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MetroTransitClient {

    private static String METRO_TRANSIT_BASE_URL = "https://svc.metrotransit.org/nextripv2";

    public static List<MetroAgency> getAgencies() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        String url = METRO_TRANSIT_BASE_URL + "/agencies";
        ResponseEntity<MetroAgency[]> response = restTemplate.getForEntity(url, MetroAgency[].class);
        MetroAgency[] agencies = response.getBody();
        return Arrays.asList(agencies);
    }

}
