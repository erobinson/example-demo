@load-data @integration
Feature: MetroTransit -> Load Metro Transit data
  These scenarios assume that the Metro Transit (https://svc.metrotransit.org/swagger/index.html) API is online and available.

  Scenario: Load agencies from metro transit API
    Given there are no agencies in the system
    When a user requests the agencies
    Then the system loads the agencies from Metro Transit
    And the user gets back a list of agencies with name and ID

  Scenario: Load routes from metro transit API
    Given there are no routes in the system
    When a user requests the routes
    Then the system loads the agencies and routes from Metro Transit
    And the user gets back a list of routes with route ID, agency ID, and route label
