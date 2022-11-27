package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Agency;
import com.example.demo.service.AgencyService;

@RestController
public class AgencyController {

    @Autowired
    private AgencyService agencyService;
    
    @GetMapping(value = "/agencies")
    public @ResponseBody List<Agency> getAllAgencies() {
        return agencyService.getAllAgencies();
    }
}
