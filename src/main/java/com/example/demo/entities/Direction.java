package com.example.demo.entities;

import com.example.demo.metro.client.MetroDirection;

public class Direction {

    public Direction()  {}

    public Direction(MetroDirection metroDirection) {
        directionId = metroDirection.getDirectionId();
        directionName = metroDirection.getDirectionName();
    }

    private Integer directionId;

    private String directionName;

    public Integer getDirectionId() {
        return directionId;
    }

    public void setDirectionId(Integer directionId) {
        this.directionId = directionId;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }
}
