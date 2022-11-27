package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Agency;
import com.example.demo.persist.AgencyRepository;

@Service
public class AgencyService {

    @Autowired
    private AgencyRepository agencyRepository;
    
    public Long agencyCount() {
        return agencyRepository.count();
    }

    public List<Agency> getAllAgencies() {
        return agencyRepository.findAll();
    }

    public void deleteAll() {
        agencyRepository.deleteAll();
    }
}
