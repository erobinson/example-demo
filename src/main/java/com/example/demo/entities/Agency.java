package com.example.demo.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import com.example.demo.metro.client.MetroAgency;

@Entity
public class Agency {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GeneratedValue
    @Column(name = "AGENCY_PRYMARY_KEY")
    private UUID primaryKey;

    @Column(name = "AGENCY_ID", nullable = false)
    private Integer agencyId;

    @Column(name = "NAME", nullable = false)
    private String name;

    public Agency() {
    }

    public Agency(MetroAgency metroAgency) {
        name = metroAgency.getAgencyName();
        agencyId = metroAgency.getAgencyId();
    }

    public UUID getPrimaryKey() {
        return primaryKey;
    }

    public String getName() {
        return name;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

}
