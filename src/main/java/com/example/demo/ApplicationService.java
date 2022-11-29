package com.example.demo;

import com.example.demo.entities.Agency;
import com.example.demo.entities.Direction;
import com.example.demo.entities.Route;
import com.example.demo.metro.client.*;
import com.example.demo.persist.AgencyRepository;
import com.example.demo.persist.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private RouteRepository routeRepository;


    public Long getAgencyCount() {
        return agencyRepository.count();
    }

    public List<Agency> getAllAgencies() {
        loadAgencies();
        return agencyRepository.findAll();
    }

    private void loadAgencies() {
        if (agencyRepository.count() <= 0) {
            List<MetroAgency> metroAgencies = MetroTransitClient.getAgencies();
            List<Agency> agencies = metroAgencies.stream().
                    map(metroAgency -> new Agency(metroAgency)).collect(Collectors.toList());
            agencyRepository.saveAll(agencies);
        }
    }

    public void deleteAll() {
        routeRepository.deleteAll();
        agencyRepository.deleteAll();
    }

    public Long getRoutesCount() {
        return routeRepository.count();
    }

    public List<Route> getAllRoutes() {
        loadRoutes();
        return routeRepository.findAll();
    }

    private void loadRoutes() {
        List<Agency> agenciesList = getAllAgencies();
        Map<Integer, Agency> agenciesMap = agenciesList.stream()
                .collect(Collectors.toMap(Agency::getAgencyId, Function.identity()));

        if (routeRepository.count() <= 0) {
            List<MetroRoute> metroRoutes = MetroTransitClient.getRoutes();
            List<Route> routes = metroRoutes.stream().
                    map(metroRoute -> new Route(metroRoute, agenciesMap)).collect(Collectors.toList());
            routeRepository.saveAll(routes);
        }
    }

    public List<Direction> getDirectionsForRoute(Route route) {
        List<MetroDirection> metroDirections = MetroTransitClient.getDirectionsForRouteId(route.getRouteId());
        List<Direction> directions = metroDirections.stream().
                map(metroDirection -> new Direction(metroDirection)).collect(Collectors.toList());
        return directions;
    }

    public Integer getTimeToNextBus(String routeSubstr, String stopSubstr, String directionSubstr) throws Exception {
        Route route = getRouteBySubstring(routeSubstr);
        Integer directionId = getDirectionBySubstring(route, directionSubstr);
        String stopCode = getStepCodeBySubstring(route, directionId, stopSubstr);
        MetroStopInformation stopInfo = MetroTransitClient.getStopInformation(route.getRouteId(), directionId, stopCode);
        return getNextDepartureTime(stopInfo);
    }

    private Route getRouteBySubstring(String routeSubstr) {
        loadRoutes();
        List<Route> routes = routeRepository.findByRouteLabelContains(routeSubstr);
        if (routes == null || routes.isEmpty()) {
            throw new IllegalArgumentException("Route not found for route: " + routeSubstr);
        }
        Route route = routes.get(0);
        if(routes.size() > 1) {
            for(Route routeExactMatch : routes) {
                if(routeExactMatch.getRouteLabel().equalsIgnoreCase(routeSubstr)) {
                    route = routeExactMatch;
                }
            }
        }
        return route;
    }

    private Integer getNextDepartureTime(MetroStopInformation stopInfo) throws Exception {
        if (stopInfo == null || stopInfo.getDepartures() == null || stopInfo.getDepartures().isEmpty()) {
            throw new Exception("No stops found for desired route");
        }
        long midnight = getMidnightTimestamp();
        long now = Instant.now().getEpochSecond();;
        Long nextDepartureTime = null;
        for (MetroDeparture departure : stopInfo.getDepartures()) {
            boolean laterToday = departure.getDepartureTime() > now && departure.getDepartureTime() < midnight;
            if (laterToday && (nextDepartureTime == null || departure.getDepartureTime() < nextDepartureTime)) {
                nextDepartureTime = departure.getDepartureTime();
            }
        }
        Integer minutesToNextBus = nextDepartureTime == null ? -1 : (int) (nextDepartureTime - now) / 60;

        return minutesToNextBus;
    }

    private long getMidnightTimestamp() {
        Set<String> zones = ZoneId.getAvailableZoneIds();
        ZoneId cst = ZoneId.of("America/Chicago");
        LocalDate today = LocalDate.now(cst);
        LocalDateTime todayMidnight = LocalDateTime.of(today, LocalTime.MIDNIGHT);
        LocalDateTime tomorrowMidnight = todayMidnight.plusDays(1);
        return tomorrowMidnight.atZone(cst).toInstant().getEpochSecond();
    }

    private Integer getDirectionBySubstring(Route route, String directionSubstr) throws Exception {
        List<MetroDirection> directions = MetroTransitClient.getDirectionsForRouteId(route.getRouteId());
        for (MetroDirection direction : directions) {
            if (direction.getDirectionName().toLowerCase().contains(directionSubstr.toLowerCase())) {
                return direction.getDirectionId();
            }
        }
        throw new IllegalArgumentException("Direction not found for route "
                + route.getRouteLabel() + " direction: " + directionSubstr);
    }

    private String getStepCodeBySubstring(Route route, Integer directionId, String stopSubstr) throws Exception {
        List<MetroPlace> stops = MetroTransitClient.getPlacesForRouteAndDirection(route.getRouteId(), directionId);
        for (MetroPlace stop : stops) {
            if (stop.getDescription().toLowerCase().contains(stopSubstr.toLowerCase())) {
                return stop.getPlaceCode();
            }
        }
        throw new IllegalArgumentException("Stop not found for for route '"
                + route.getRouteLabel() + "' direction id " + directionId + " stop " + stopSubstr);
    }
}
