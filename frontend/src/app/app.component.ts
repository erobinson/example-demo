import { Component } from '@angular/core';
import { MatStepper } from '@angular/material/stepper';
import { AppService } from './app.service';
import { Agency } from './entities/agency.model';
import { Route } from './entities/route.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  agencies = new Array<Agency>();
  loading = true;
  agencySelected?: Agency;
  routeSelected?: Route;
  routes = new Array<Route>();

  constructor(private appService: AppService) { }

  ngOnInit() {
    this.appService.getAgencies().subscribe(response => {
      this.agencies = response;
      this.loading = false;
    });
    this.appService.getRoutes().subscribe(response => this.routes = response);
  }

  onAgencyClick(stepper: MatStepper, agency: Agency) {
    this.agencySelected = agency;
    stepper.next();
  }

  onRouteClick(stepper: MatStepper, route: Route) {
    this.routeSelected = route;
    stepper.next();
  }

  onReset(stepper: MatStepper) {
    stepper.reset();
    this.agencySelected = undefined;
  }

  getRoutesForAgency(): Array<Route> {
    let routes = new Array<Route>();
    for(let route of this.routes) {
      if(route.agency.agencyId == this.agencySelected?.agencyId) {
        routes.push(route);
      }
    }
    return routes;
  }
}
