package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Agency;
import com.example.demo.metro.client.MetroAgency;
import com.example.demo.metro.client.MetroTransitClient;
import com.example.demo.persist.AgencyRepository;

@Service
public class AgencyService {

    @Autowired
    private AgencyRepository agencyRepository;
    
    
    public Long agencyCount() {
        return agencyRepository.count();
    }

    public List<Agency> getAllAgencies() {
        if(agencyRepository.count() <= 0) {
            List<MetroAgency> metroAgencies = MetroTransitClient.getAgencies();
            List<Agency> agencies = new ArrayList<>();
            for(MetroAgency metroAgency: metroAgencies) {
                agencies.add(new Agency(metroAgency));
            }
            agencyRepository.saveAll(agencies);
        }
        return agencyRepository.findAll();
    }

    public void deleteAll() {
        agencyRepository.deleteAll();
    }
}
