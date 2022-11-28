package com.example.demo.entities;

import com.example.demo.metro.client.MetroDirection;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Direction {

    public Direction()  {}

    public Direction(MetroDirection metroDirection) {
        directionId = metroDirection.getDirectionId();
        directionName = metroDirection.getDirectionName();
    }

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GeneratedValue
    @Column(name = "DIRECTION_PRIMARY_KEY")
    private UUID primaryKey;

    @Column(name = "DIRECTION_ID", nullable = false)
    private Integer directionId;

    @Column(name = "DIRECTION_NAME", nullable = false)
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
