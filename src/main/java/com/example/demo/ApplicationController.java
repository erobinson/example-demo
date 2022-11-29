package com.example.demo;

import com.example.demo.entities.Agency;
import com.example.demo.entities.Route;
import com.example.demo.entities.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService appService;

    @GetMapping(value = "/agencies")
    public @ResponseBody List<Agency> getAgencies() {
        return appService.getAllAgencies();
    }

    @GetMapping(value = "/routes")
    public @ResponseBody List<Route> getRoutes() {
        return appService.getAllRoutes();
    }

    @GetMapping(value = "/stops/{route}/{direction}")
    public @ResponseBody List<Stop> getStopsForRoute(@PathVariable("route") String routeSubstr,
                                                     @PathVariable("direction") String directionSubstr) throws Exception {
        return appService.getStopsForRoute(routeSubstr, directionSubstr);
    }

    @GetMapping(value = "/next-departure-time/{route}/{stop}/{direction}")
    public @ResponseBody Integer getNextDepartureTime(@PathVariable("route") String routeSubstr,
                                                      @PathVariable("stop") String stopSubstr,
                                                      @PathVariable("direction") String directionSubstr) throws Exception {
        return appService.getNextDepartureTime(routeSubstr, stopSubstr, directionSubstr);
    }
}
