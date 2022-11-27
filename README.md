# Example application to build an API to check bus statuses

[MetroTransit API](http://svc.metrotransit.org/)
[MetroTransit Swagger API](https://svc.metrotransit.org/swagger/index.html)

## Requirements
“BUS ROUTE” will be a substring of the bus route name which is only in one bus route
“BUS STOP NAME” will be a substring of the bus stop name which is only in one bus stop on that route
“DIRECTION” will be “north” “east” “west” or “south”
Eg, if you wanted to know the next bus leaving from our Brooklyn Park campus to our downtown campus:
$ go run nextbus.go “Express -Target -Hwy 252 and 73rd Av P&R -Mpls” “Target North Campus Building F” “south”2 Minutes
(note that that won’t return anything if the last bus for the day has already left)
Or if you wanted to take the light rail from downtown to the Mall of America or the Airport:$ nextbus.py “METRO Blue Line” “Target Field Station Platform 1” “south”8 Minutes

## Approach

Leveraging Spring Boot to build out solution. Spring is well known amoung java developers and easy to understand for non-java developers. It is also widely used and thus has lots of documentation, robust integrations, many StackOverflow posts, and other resources to help developers use & expand this solution.

To get started, check out the Cucumber <em>src/test/resources/specifications/*.feature</em> files to see the requirements, src/test/java/** code for the tests, and src/main/java/** code for the implementation.

