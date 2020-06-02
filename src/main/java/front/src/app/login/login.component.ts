import { Component, OnInit } from '@angular/core';
import {AuthUser} from '../models/AuthUser';
import {AuthenticationService} from '../services/authentication-service';
import {AuthService, GoogleLoginProvider} from "angularx-social-login";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {environment} from "../../environments/environment";
import {UsernameToken} from "../models/username-token";
// import {AuthService, GoogleLoginProvider, SocialUser} from "angularx-social-login";
// import {UsernameToken} from "../models/username-token";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  // private socialUser: SocialUser;
  alert = false;
  user: AuthUser = {
    login: '',
    password: ''
  };

  formModel = {
    UserName: '',
    Password: ''
  }
  constructor(private http: HttpClient, private router: Router, private auth: AuthenticationService, private authService: AuthService) { }

  ngOnInit() {
  }

  signIn() {
    this.http.post(environment.apiUrl+'/api/v1/auth/login' , this.user)
      .subscribe((resp: UsernameToken) => {
        // this.router.navigate(['profile']);
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
        //alert('Неверный логин или пароль!');
      });
    // this.auth.signIn(this.user);
    // этот метод вызывается когда на форме нажимают кнопку войти
  }

  signInWithGoogle() {
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID).then(socialUser => this.auth.signInGoogle() );
    // этот метод вызывается когда на форме нажимают кнопку войти
  }

  // signInWithGoogle(): void {
  //   this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);

  //   this.authService.authState.subscribe((socialUser) => {
  //     this.socialUser = socialUser;
  //     // this.loggedIn = (socialUser != null);
  //     // if (this.loggedIn && !this.isAuthenticated) {
  //       // this.email = user.email;
  //       // this.photo = user.photoUrl;
  //       this.http.post('http://localhost:9090/api/v1/auth/login_oauth2',
  //         {
  //           "provider": socialUser.provider,
  //           "id": socialUser.id,
  //           "email": socialUser.email,
  //           "name": socialUser.name,
  //           "photoUrl": socialUser.photoUrl,
  //           "firstName": socialUser.firstName,
  //           "lastName": socialUser.lastName,
  //           "authToken": socialUser.authToken,
  //           "idToken": socialUser.idToken,
  //           "authorizationCode": socialUser.authorizationCode
  //         }).subscribe((data: UsernameToken) => {
  //         localStorage.setItem('token', data.token);
  //         localStorage.setItem('roles', data.roles.toString());
  //         localStorage.setItem('username', data.username);
  //       });
  //     // }
  //   });
 //}

  test() {
    this.auth.testRequest();
  }
}



// // import {Component, OnInit} from '@angular/core';
// // import {AuthService, FacebookLoginProvider, GoogleLoginProvider, SocialUser} from "angularx-social-login";
// // import {Router} from "@angular/router";
// // import {CookieService} from "ngx-cookie-service";
// // import {DomSanitizer} from "@angular/platform-browser";
// // import {OktaAuthService} from "@okta/okta-angular";
// // import {HttpClient} from "@angular/common/http";
// // import {User} from "../cabinet/user";
// //
// // @Component({
// //   selector: 'app-login-page',
// //   templateUrl: './login.component.html',
// //   styleUrls: ['./login.component.css']
// // })
// // export class LoginComponent implements OnInit {
// //
// //   constructor(private http: HttpClient) {
// //
// //   }
// //
// //   URL: string = 'http://localhost:9090';
// //   isAuthenticated: boolean;
// //
// //
// //   ngOnInit() {
// //   }
// //
// //
// // }
// import {Component, OnInit} from '@angular/core';
// import {Router, ActivatedRoute} from '@angular/router';
// import {AuthenticationService} from "../services/authentication-service";
// import {UsernameToken} from "../models/username-token";
// import {HttpClient} from "@angular/common/http";
//
// // import { AuthenticationService } from './auth.service';
//
// @Component({
//   selector: 'app-login',
//   templateUrl: './login.component.html',
//   styleUrls: ['./login.component.css']
// })
// export class LoginComponent implements OnInit {
//
//   username: string;
//   password: string;
//   errorMessage = 'Invalid Credentials';
//   successMessage: string;
//   invalidLogin = false;
//   loginSuccess = false;
//
//   constructor(
//     private route: ActivatedRoute,
//     private router: Router,
//     private authenticationService: AuthenticationService,
//     private http: HttpClient) {
//   }
//
//   ngOnInit() {
//   }
//
//   handleLogin() {
//     // this.authenticationService.attemptAuth(this.username, this.password).subscribe((data: UsernameToken) => {
//     const parameter = JSON.stringify({"login": this.username, "password": this.password});
//
//     this.http.post('http://localhost:9090/api/v1/auth/login', parameter).subscribe((data: UsernameToken) => {
//       localStorage.setItem("jwtToken", data.token);
//     });//.subscribe((result)=> {
//     // this.http.post('http://localhost:9090/api/v1/auth/login', {
//     //   'login': this.username,
//     //   'password': this.password
//     // }).subscribe((data: UsernameToken) => {
//     //   localStorage.setItem("jwtToken", data.token);
//     // });
//     //   this.invalidLogin = false;
//     //   this.loginSuccess = true;
//     //   this.successMessage = 'Login Successful.';
//     //   this.router.navigate(['/hello-world']);
//     // }, () => {
//     //   this.invalidLogin = true;
//     //   this.loginSuccess = false;
//     // });
//   }
//   }
