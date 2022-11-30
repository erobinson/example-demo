import { Component } from '@angular/core';
import { MatStepper } from '@angular/material/stepper';
import { AppService } from './app.service';
import { Agency } from './entities/agency.model';
import { Route } from './entities/route.model';
import { Stop } from './entities/stop.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  agencies = new Array<Agency>();
  loading = true;
  loadingStops = true;
  loadingDeparture = true;
  encounteredError = false;
  agencySelected?: Agency;
  routeSelected?: Route;
  routes = new Array<Route>();
  directions = ["North", "South", "East", "West"];
  directionSelected?: string;
  stopsForRoute = new Array<Stop>();
  stopSelected?: Stop;

  routeSubstr?: string;
  stopSubstr?: string;
  nextDepartureTime?: number;

  onReset(stepper: MatStepper) {
    stepper.reset();
    this.loadingStops = true;
    this.loadingDeparture = true;
    this.encounteredError = false;
    this.agencySelected = undefined;
    this.routeSelected = undefined;
    this.stopsForRoute = new Array<Stop>();
    this.stopSelected = undefined;
    this.directionSelected = undefined;
    this.routeSubstr = undefined;
    this.stopSubstr = undefined;
    this.nextDepartureTime = undefined;
  }

  constructor(private appService: AppService) { }

  ngOnInit() {
    this.appService.getAgencies().subscribe(response => {
      this.agencies = response;
      this.appService.getRoutes().subscribe(response => {
        this.routes = response;
        this.loading = false;
      });
    });
  }

  onAgencyClick(stepper: MatStepper, agency: Agency) {
    this.agencySelected = agency;
    stepper.next();
  }

  onRouteClick(stepper: MatStepper, route: Route) {
    this.routeSelected = route;
    stepper.next();
  }

  onDirectionClick(stepper: MatStepper, direction: string) {
    this.directionSelected = direction;
    this.loadingStops = true;
    stepper.next();
    if (this.routeSelected) {
      let getStopsCall = this.appService.getStopsForRoute(this.routeSelected.routeLabel, this.directionSelected);
      getStopsCall.subscribe(response => {
        this.stopsForRoute = response;
        this.loadingStops = false;
      }, error => {
        console.error(error);
        this.loadingStops = false;
        this.stopsForRoute = new Array<Stop>();
      });
    } else {
      console.error("No route selected");
    }
  }

  onStopClick(stepper: MatStepper, stop: Stop) {
    this.stopSelected = stop;
    this.routeSubstr = this.routeSelected?.routeLabel;
    this.stopSubstr = this.stopSelected?.description;
    stepper.next();
  }

  onSubmit(stepper: MatStepper) {
    this.loadingDeparture = true;
    stepper.next();
    if (this.routeSubstr && this.stopSubstr && this.directionSelected) {
      let call = this.appService.getNextDepartureTime(this.routeSubstr, this.stopSubstr, this.directionSelected);
      call.subscribe(response => {
        this.loadingDeparture = false;
        this.nextDepartureTime = response;
      },
      error => {
        console.error(error);
        this.loadingDeparture = false;
        this.encounteredError = true;
      });
    } else {
      console.log("Route or stop was empty or undefined!");
    }
  }

  getRoutesForAgency(): Array<Route> {
    let routes = new Array<Route>();
    for (let route of this.routes) {
      if (route.agency.agencyId == this.agencySelected?.agencyId) {
        routes.push(route);
      }
    }
    return routes;
  }
}
