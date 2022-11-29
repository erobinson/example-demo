package com.example.demo;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class TimeToNextBusSteps {

    @Autowired
    private ApplicationService appService;

    private String routeSubstr;
    private String stopSubstr;
    private String direction;
    private Integer nextBusTime;

    @After("@next-bus")
    public void after() {
        appService.deleteAll();
    }

    @Given("a unique {string} to identify the bus\\/train route")
    public void a_unique_to_identify_the_bus_train_route(String routeSubstr) {
        this.routeSubstr = routeSubstr;
    }

    @Given("a unique {string} to identify the bus\\/train stop")
    public void a_unique_to_identify_the_bus_train_stop(String stopSubstr) {
        this.stopSubstr = stopSubstr;
    }

    @Given("a {string} for the direction of the bus\\/train")
    public void a_for_the_direction_of_the_bus_train(String direction) {
        this.direction = direction;
    }

    @When("the user requests the next bus time")
    public void the_user_requests_the_next_bus_time() throws Exception {
        nextBusTime = appService.getTimeToNextBus(routeSubstr, stopSubstr, direction);
    }

    @Then("they get the number of minutes until the next bus\\/train")
    public void they_get_the_number_of_minutes_until_the_next_bus_train() {
        assertNotNull(nextBusTime);
    }
}
