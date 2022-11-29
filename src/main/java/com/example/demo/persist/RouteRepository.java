package com.example.demo.persist;

import com.example.demo.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface RouteRepository extends JpaRepository<Route, UUID>, JpaSpecificationExecutor<Route> {

    Route findByRouteLabelContains(String routeSubstr);
}
