package com.example.demo;

import com.example.demo.entities.Agency;
import com.example.demo.entities.Direction;
import com.example.demo.entities.Route;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class LoadMTDataSteps {

    @Autowired
    private ApplicationService appService;

    private List<Agency> agencies;

    private List<Route> routes;
    private Route route;
    private List<Direction> directionsForRoute;

    @After("@load-data")
    public void after() {
        appService.deleteAll();
    }

    @Given("there are no agencies in the system")
    public void there_are_no_agencies_in_the_system() {
        assertEquals("There were agencies in the system!", 0, appService.getAgencyCount().intValue());
    }

    @Given("there are no routes in the system")
    public void there_are_no_routes_in_the_system() {
        assertEquals("There were routes in the system!", 0, appService.getRoutesCount().intValue());
    }


    @Given("a random route from Metro Transit")
    public void a_random_route_from_Metro_Transit() {
        routes = appService.getAllRoutes();
        route = routes.get(new Random().nextInt(routes.size()));
    }

    @When("a user requests the agencies")
    public void a_user_requests_to_get_the_agencies() {
        agencies = appService.getAllAgencies();
    }

    @When("a user requests the routes")
    public void a_user_requests_the_routes() {
        routes = appService.getAllRoutes();
        agencies = appService.getAllAgencies();
    }
    @When("the user requests the directions for that route")
    public void the_user_requests_the_directions_for_that_route() {
        directionsForRoute = appService.getDirectionsForRoute(route);
    }

    @Then("the system loads the agencies from Metro Transit")
    public void the_system_loads_the_agencies_from_Metro_Transit() {
        assertFalse("The agencies were not loaded from Metro Transit!", agencies.isEmpty());
    }

    @Then("the user gets back a list of agencies with name and ID")
    public void the_user_gets_back_a_list_of_agencies_with_name_and_ID() {
        assertNotNull("The first agency was missing a name!", agencies.get(0).getName());
        assertNotNull("The first agency was missing an agency ID!", agencies.get(0).getAgencyId());
    }

    @Then("the system loads the agencies and routes from Metro Transit")
    public void the_system_loads_the_agencies_and_routes_from_Metro_Transit() {
        assertFalse("The agencies were not loaded from Metro Transit!", agencies.isEmpty());
        assertFalse("The routes were not loaded from Metro Transit!", routes.isEmpty());
    }

    @Then("the user gets back a list of routes with route ID, agency ID, and route label")
    public void the_user_gets_back_a_list_of_routes_with_route_ID_agency_ID_and_route_label() {
        Route firstRoute = routes.get(0);
        assertNotNull("The first agency ID was missing!", firstRoute.getAgency().getAgencyId());
        assertNotNull("The first route ID was missing!", firstRoute.getRouteId());
        assertNotNull("The first route label was missing!", firstRoute.getRouteLabel());
    }

    @Then("the system loads the routes and directions")
    public void the_system_loads_the_routes_and_directions() {
        assertTrue("The directions were not loaded from Metro Transit!", directionsForRoute != null && !directionsForRoute.isEmpty());
    }

    @Then("the user gets back a list of directions with direction id and name")
    public void the_user_gets_back_a_list_of_directions_with_direction_id_and_name() {
        Direction firstDirection = directionsForRoute.get(0);
        assertNotNull("The first direction ID was missing!", firstDirection.getDirectionId());
        assertNotNull("The first direction name was missing!", firstDirection.getDirectionName());
    }
}
