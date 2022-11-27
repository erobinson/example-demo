package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Agency;
import com.example.demo.service.AgencyService;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoadMTDataSteps {

    @Autowired
    private AgencyService agencyService;
    private List<Agency> agencies;
    
    @After("@load-data")
    public void after() {
        agencyService.deleteAll();
    }
    
    @Given("there are no agencies in the system")
    public void there_are_no_agencies_in_the_system() {
        assertEquals("There were agencies in the system!", 0, agencyService.agencyCount().intValue());
    }

    @When("a user requests to get the agencies")
    public void a_user_requests_to_get_the_agencies() {
        agencies = agencyService.getAllAgencies();
    }

    @Then("the system loads the agencies from Metro Transit")
    public void the_system_loads_the_agencies_from_Metro_Transit() {
        assertFalse("The agencies were not loaded from Metro Transit!", agencies.isEmpty());
    }

    @Then("the user gets back a list of agencies with name and ID")
    public void the_user_gets_back_a_list_of_agencies_with_name_and_ID() {
        assertFalse("The agencies were not loaded from Metro Transit!", agencies.isEmpty());
    }

}
