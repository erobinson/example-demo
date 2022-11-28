package com.example.demo.persist;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entities.Route;

public interface RouteRepository extends JpaRepository<Route, UUID>, JpaSpecificationExecutor<Route> {

}
