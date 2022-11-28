package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Agency;
import com.example.demo.entities.Route;
import com.example.demo.metro.client.MetroAgency;
import com.example.demo.metro.client.MetroRoute;
import com.example.demo.metro.client.MetroTransitClient;
import com.example.demo.persist.AgencyRepository;
import com.example.demo.persist.RouteRepository;

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
            List<Agency> agencies = new ArrayList<>();
            for (MetroAgency metroAgency : metroAgencies) {
                agencies.add(new Agency(metroAgency));
            }
            agencyRepository.saveAll(agencies);
        }
    }

    public void deleteAll() {
        agencyRepository.deleteAll();
    }

    public Long getRoutesCount() {
        return routeRepository.count();
    }

    public List<Route> getAllRoutes() {
        loadAgencies();
        loadRoutes();
        return routeRepository.findAll();
    }

    private void loadRoutes() {
        if (routeRepository.count() <= 0) {
            List<MetroRoute> metroRoutes = MetroTransitClient.getRoutes();
            List<Route> routes = new ArrayList<>();
            for (MetroRoute metroRoute : metroRoutes) {
                routes.add(new Route(metroRoute));
            }
            routeRepository.saveAll(routes);
        }
    }
}
