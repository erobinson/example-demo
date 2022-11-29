package com.example.demo.metro.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

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

    public static List<MetroDirection> getDirectionsForRouteId(String routeId) {
        String url = METRO_TRANSIT_BASE_URL + "/directions/" + routeId;
        logger.info("Getting directions from metro transit {}...", url);
        ResponseEntity<MetroDirection[]> response = restTemplate.getForEntity(url, MetroDirection[].class);
        MetroDirection[] directions = response.getBody();
        logger.info("Successfully retrieved {} directions from metro transit.", directions.length);
        return Arrays.asList(directions);
    }

    public static List<MetroPlace> getPlacesForRouteAndDirection(String routeId, Integer directionId) {
        String url = METRO_TRANSIT_BASE_URL + "/stops/" + routeId + "/" + directionId;
        logger.info("Getting stops from metro transit {}...", url);
        ResponseEntity<MetroPlace[]> response = restTemplate.getForEntity(url, MetroPlace[].class);
        MetroPlace[] stops = response.getBody();
        logger.info("Successfully retrieved {} stops from metro transit.", stops.length);
        return Arrays.asList(stops);
    }

    public static MetroStopInformation getStopInformation(String routeId, Integer directionId, String placeCode) {
        String url = METRO_TRANSIT_BASE_URL + "/" + routeId + "/" + directionId + "/" + placeCode;
        logger.info("Getting stop information from metro transit {}...", url);
        ResponseEntity<MetroStopInformation> response = restTemplate.getForEntity(url, MetroStopInformation.class);
        MetroStopInformation stopInformation = response.getBody();
        logger.info("Successfully retrieved stop information from metro transit.");
        return stopInformation;
    }
}
