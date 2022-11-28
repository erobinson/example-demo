package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Agency;

@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService appService;
    
    @GetMapping(value = "/agencies")
    public @ResponseBody List<Agency> getAllAgencies() {
        return appService.getAllAgencies();
    }
}
