package com.example.demo;

import com.example.demo.entities.Agency;
import com.example.demo.entities.Direction;
import com.example.demo.entities.Route;
import com.example.demo.metro.client.MetroAgency;
import com.example.demo.metro.client.MetroDirection;
import com.example.demo.metro.client.MetroRoute;
import com.example.demo.metro.client.MetroTransitClient;
import com.example.demo.persist.AgencyRepository;
import com.example.demo.persist.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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
}
