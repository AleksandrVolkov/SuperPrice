import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
// styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Project';
  isAuthenticated: boolean;

  constructor() {//public oktaAuth: OktaAuthService
  }

  async ngOnInit() {
    // this.isAuthenticated = await this.oktaAuth.isAuthenticated();
    // this.oktaAuth.$authenticationState.subscribe(
    //   (isAuthenticated: boolean)  => this.isAuthenticated = isAuthenticated
    // );
  }
}
