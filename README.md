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

Could have built a simple command line tool like a python script. That would have solved the described problem, but wanted to build something more robust where you could potentially add more features and/or scale the application.

To get started, check out the Cucumber <em>src/test/resources/specifications/*.feature</em> files to see the requirements, src/test/java/** code for the tests, and src/main/java/** code for the implementation.

## Running

There is a build script `build-and-run.sh` that builds the frontend and backend docker containers and then runs them. The only pre-requisite is having docker installed.

## Development

When devloping locally, you can build and run the backend spring boot application using maven. You must have JDK 11 installed. Then run 

```
cd backend
./mvnw clean install

# or to run the backend locally
cd backend
./mvnw spring-boot:run

```

Then you can use curl to get a list of [agencies](http://localhost:8080/agencies) in your browser. Or:
 - [List of routes](http://localhost:8080/routes)
 - [List of stops](http://localhost:8080/stops/Blue/north)
 - [Next Departure Time](http://localhost:8080/next-departure-time/Blue/mall/north)

The frontend requires Node v18.x.x installed. You can run it using the following commands. This assumes that you have the backend running via maven or the backend docker container.

```
cd frontend
npm install
npm start
```

[open localhost:4200](http://localhost:4200)

## Issues

The Metro Transit data/API seems to be conflicting. For example, [stop](https://svc.metrotransit.org/nextripv2/2875) "7th St N & Twins Way" with ID 2875, shows a departure by bus route 22 (see JSON below). However, when pulling up the [stops](https://svc.metrotransit.org/nextripv2/stops/22/0) for route 22 Northbound, the list of stops doesn't include "7th St N & Twins Way".

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
Their website uses some undocumented API calls which are missing from the swagger API documentation. 
For example, API for [schedule routes](https://svc.metrotransit.org/schedule/routes) which contains the url parameter to get the route details [route 2 details](https://svc.metrotransit.org/schedule/routedetails/2) that include all the stop & schedule information are both not documented.