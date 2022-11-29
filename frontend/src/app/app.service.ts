import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Agency } from './entities/agency.model';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http:HttpClient) { }

  getAgencies(): Observable<Array<Agency>> {
    return this.http.get<Array<Agency>>("/agencies");
  }
}
