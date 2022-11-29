package com.example.demo.metro.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MetroStopInformation {

    private List<MetroPlace> stops;

    // not including "alerts" [] from response

    private List<MetroDeparture> departures;

    public List<MetroPlace> getStops() {
        return stops;
    }

    public void setStops(List<MetroPlace> stops) {
        this.stops = stops;
    }

    public List<MetroDeparture> getDepartures() {
        return departures;
    }

    public void setDepartures(List<MetroDeparture> departures) {
        this.departures = departures;
    }
}
