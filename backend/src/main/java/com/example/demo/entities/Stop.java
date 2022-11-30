package com.example.demo.entities;

import com.example.demo.metro.client.MetroPlace;

public class Stop {

    private String placeCode;
    private String description;

    public Stop() {
    }

    public Stop(MetroPlace metroStop) {
        placeCode = metroStop.getPlaceCode();
        description = metroStop.getDescription();
    }


    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
