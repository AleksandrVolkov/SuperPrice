import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../services/authentication-service";

@Component({
  selector: 'app-user',
  templateUrl: './admin.html',
  styleUrls: ['./admin.css']
})
export class AdminPanelComponent implements OnInit {

  constructor(public auth: AuthenticationService) { }

  ngOnInit() {
  }

}
