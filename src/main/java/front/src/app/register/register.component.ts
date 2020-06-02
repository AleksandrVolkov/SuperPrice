import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../services/authentication-service";
import {RegisterUser} from "../models/RegisterUser";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {environment} from "../../environments/environment";
import {UsernameToken} from "../models/username-token";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  alert = false;
  user: RegisterUser =  {
    userName: '',
    firstName: '',
    lastName: '',
    picture: 'picture',
    email: '',
    password: ''
}

  constructor(private http: HttpClient, private router: Router, private auth: AuthenticationService) { }

  ngOnInit() {}

  register() {
    this.user.userName = this.user.email;
    this.http.post(environment.apiUrl+'/api/v1/auth/registration' , this.user)
      .subscribe((resp: UsernameToken) => {
        localStorage.setItem('token', resp.token);
        localStorage.setItem('roles', resp.roles.toString());
        localStorage.setItem('username', resp.username);
        const nav = localStorage.getItem('login_navigate');
        if(nav == 'home/backet'){
          localStorage.setItem('login_navigate', '');
          this.router.navigate(['home/backet']);
        } else {
          this.router.navigate(['']);
        }
      }, error => {
        this.alert = true;
        //alert('Пользователь с таким логином уже существует!');
      });
    //this.auth.register(this.user);
  }
}
