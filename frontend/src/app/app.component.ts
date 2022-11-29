import { Component } from '@angular/core';
import { AppService } from './app.service';
import { Agency } from './entities/agency.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  agencies = new Array<Agency>();

  constructor(private appService: AppService) {}

  ngOnInit() {
    this.appService.getAgencies().subscribe(response => this.agencies = response);
  }
}
