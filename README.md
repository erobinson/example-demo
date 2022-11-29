# Example application to build an API to check bus statuses

[MetroTransit API](http://svc.metrotransit.org/)
[MetroTransit Swagger API](https://svc.metrotransit.org/swagger/index.html)

## Requirements
- “BUS ROUTE” will be a substring of the bus route name which is only in one bus route
- “BUS STOP NAME” will be a substring of the bus stop name which is only in one bus stop on that route
- “DIRECTION” will be “north” “east” “west” or “south”

Eg, if you wanted to know the next bus leaving from our Brooklyn Park campus to our downtown campus:
- $ go run nextbus.go “Express -Target -Hwy 252 and 73rd Av P&R -Mpls” “Target North Campus Building F” “south”
  - Result: 2 Minutes
  - (note that that won’t return anything if the last bus for the day has already left)

Or if you wanted to take the light rail from downtown to the Mall of America or the Airport:
  - $ nextbus.py “METRO Blue Line” “Target Field Station Platform 1” “south” 
    - Result: 8 Minutes

## Approach

Leveraging Spring Boot to build out solution. Spring is well known amoung java developers and easy to understand for non-java developers. It is also widely used and thus has lots of documentation, robust integrations, many StackOverflow posts, and other resources to help developers use & expand this solution.

I could have built a simple command line tool like a python script. That would have solved the described problem, but I wanted to build something more robust where you could potentially add a UI, or scale the application or build out more features.

To get started, check out the Cucumber <em>src/test/resources/specifications/*.feature</em> files to see the requirements, src/test/java/** code for the tests, and src/main/java/** code for the implementation.

## Issues

Some of the Metro Transit data/API seems to be conflicting. For example, [stop](https://svc.metrotransit.org/nextripv2/2875) "7th St N & Twins Way" with ID 2875, shows a departure by bus route 22 (see JSON below). However when pulling up the [stops](https://svc.metrotransit.org/nextripv2/stops/22/0) for route 22 Northbound, the list of stops doesn't include "7th St N & Twins Way".

```
    {
      "actual": true,
      "trip_id": "22201337-AUG22-MVS-BUS-Weekday-01",
      "stop_id": 2875,
      "departure_text": "17 Min",
      "departure_time": 1669693634,
      "description": "Brklyn Ctr Tc / N Lyndale / Via Bryant",
      "route_id": "22",
      "route_short_name": "22",
      "direction_id": 0,
      "direction_text": "NB",
      "terminal": "C",
      "schedule_relationship": "Scheduled"
    }
```

