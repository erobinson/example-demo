import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Agency } from './entities/agency.model';
import { Route } from './entities/route.model';
import { Stop } from './entities/stop.model';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) { }

  getAgencies(): Observable<Array<Agency>> {
    return this.http.get<Array<Agency>>("/api/agencies");
  }

  getRoutes(): Observable<Array<Route>> {
    return this.http.get<Array<Route>>("/api/routes");
  }

  getStopsForRoute(routeLabel: string, direction: string): Observable<Array<Stop>> {
    let url = `/api/stops/${routeLabel}/${direction}`;
    return this.http.get<Array<Stop>>(url);
  }

  getNextDepartureTime(routeSubstr: string, stopSubstr: string, directionSubstr: string): Observable<number> {
    let url = `/api/next-departure-time/${routeSubstr}/${stopSubstr}/${directionSubstr}`;
    return this.http.get<number>(url);
  }
}
