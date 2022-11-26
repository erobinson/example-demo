# @routes @integration
# Feature: MetroTransit -> Load Metro Transit (mt) data
#     These scenarios assume that the MetroTransit (https://svc.metrotransit.org/swagger/index.html) API is online and available.

#     Scenario: Load agencies from metro transit API
#         Given there are no agencies in the system
#         When a user requests to get the agencies
#         Then the system loads the agencies from Metro Transit
#         And the user gets back a list of agencies with name and ID
@dev
Feature: the version can be retrieved
  Scenario: client makes call to GET /version
    When the client calls /version
    Then the client receives status code of 200
    And the client receives server version 1.0