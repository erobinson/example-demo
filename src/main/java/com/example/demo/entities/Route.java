package com.example.demo.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import com.example.demo.metro.client.MetroRoute;

@Entity
public class Route {

    public Route() {
    }

    public Route(MetroRoute metroRoute) {
        agencyId = metroRoute.getAgencyId();
        routeId = metroRoute.getRouteId();
        routeLabel = metroRoute.getRouteLabel();
    }

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GeneratedValue
    @Column(name = "AGENCY_PRYMARY_KEY")
    private UUID primaryKey;

    @Column(name = "AGENCY_ID", nullable = false)
    private Integer agencyId;

    @Column(name = "ROUTE_ID", nullable = false)
    private String routeId;

    @Column(name = "ROUTE_LABEL", nullable = false)
    private String routeLabel;

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteLabel() {
        return routeLabel;
    }

    public void setRouteLabel(String routeLabel) {
        this.routeLabel = routeLabel;
    }

    public UUID getPrimaryKey() {
        return primaryKey;
    }
}
