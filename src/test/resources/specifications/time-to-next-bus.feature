@integration @next-bus
Feature: MetroTransit -> Time to next bus/train

  Scenario Outline: Get the time to next bus
    Given a unique "<route substring>" to identify the bus/train route
    And a unique "<stop name>" to identify the bus/train stop
    And a "<direction>" for the direction of the bus/train
    When the user requests the next bus time
    Then they get the number of minutes until the next bus/train

    Examples:
      | route substring | stop name                       | direction |
      | 724             | Target North Campus             | north     |
      | METRO Blue Line | Target Field Station Platform 1 | south     |

  Scenario Outline: Get an exception when the stop is not valid
    Given a unique "<route substring>" to identify the bus/train route
    And a unique "<stop name>" to identify the bus/train stop
    And a "<direction>" for the direction of the bus/train
    When the user requests the next bus time
    Then they get an empty response or an exception that the stop was invalid

    Examples:
      | route substring                                | stop name                      | direction |
      | Express -Target -Hwy 252 and 73rd Av P&R -Mpls | Target North Campus Building F | blah      |
      | Express -Target -Hwy 252 and 73rd Av P&R -Mpls | Target North Blah Blah         | south     |
      | Express -Target -Hwy 252 Blah Blah             | Target North Campus Building F | south     |
