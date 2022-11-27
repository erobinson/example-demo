@load-data @integration
Feature: MetroTransit -> Load Metro Transit (mt) data
    These scenarios assume that the MetroTransit (https://svc.metrotransit.org/swagger/index.html) API is online and available.

    Scenario: Load agencies from metro transit API
        Given there are no agencies in the system
        When a user requests to get the agencies
        Then the system loads the agencies from Metro Transit
        And the user gets back a list of agencies with name and ID
