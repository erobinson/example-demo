package com.example.demo.metro.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MetroDeparture {

    private Boolean actual;

    @JsonProperty("trip_id")
    private String tripId;

    @JsonProperty("stop_id")
    private Integer stopId;

    @JsonProperty("departure_text")
    private String departureText;

    @JsonProperty("departure_time")
    private Long departureTime;

    private String description;

    @JsonProperty("route_id")
    private Integer routeId;

    @JsonProperty("route_short_name")
    private String routeShortName;

    @JsonProperty("direction_id")
    private Integer directionId;

    @JsonProperty("direction_text")
    private String directionText;

    public Boolean getActual() {
        return actual;
    }

    public void setActual(Boolean actual) {
        this.actual = actual;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public Integer getStopId() {
        return stopId;
    }

    public void setStopId(Integer stopId) {
        this.stopId = stopId;
    }

    public String getDepartureText() {
        return departureText;
    }

    public void setDepartureText(String departureText) {
        this.departureText = departureText;
    }

    public Long getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Long departureTime) {
        this.departureTime = departureTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getRouteShortName() {
        return routeShortName;
    }

    public void setRouteShortName(String routeShortName) {
        this.routeShortName = routeShortName;
    }

    public Integer getDirectionId() {
        return directionId;
    }

    public void setDirectionId(Integer directionId) {
        this.directionId = directionId;
    }

    public String getDirectionText() {
        return directionText;
    }

    public void setDirectionText(String directionText) {
        this.directionText = directionText;
    }

    public String getScheduleRelationship() {
        return scheduleRelationship;
    }

    public void setScheduleRelationship(String scheduleRelationship) {
        this.scheduleRelationship = scheduleRelationship;
    }

    @JsonProperty("schedule_relationship")
    private String scheduleRelationship;

}
