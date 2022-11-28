package com.example.demo.entities;

import com.example.demo.metro.client.MetroRoute;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Map;
import java.util.UUID;

@Entity
public class Route {

    public Route() {
    }

    public Route(MetroRoute metroRoute, Map<Integer, Agency> agencies) {
        agency = agencies.get(metroRoute.getAgencyId());
        routeId = metroRoute.getRouteId();
        routeLabel = metroRoute.getRouteLabel();
    }

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GeneratedValue
    @Column(name = "ROUTE_PRIMARY_KEY")
    private UUID primaryKey;

    @ManyToOne
    @JoinColumn(name="AGENCY_PRIMARY_KEY")
    private Agency agency;

    @Column(name = "ROUTE_ID", nullable = false)
    private String routeId;

    @Column(name = "ROUTE_LABEL", nullable = false)
    private String routeLabel;

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
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
