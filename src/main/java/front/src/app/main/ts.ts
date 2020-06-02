import { Component, OnInit ,OnChanges} from '@angular/core';

import { DomSanitizer } from '@angular/platform-browser';

import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
// import { OktaAuthService } from '@okta/okta-angular';
//import{ DemoComponent } from '../shared/okta/social';
import { AuthService, SocialUser, GoogleLoginProvider, FacebookLoginProvider } from 'angularx-social-login';
import { HttpClient } from '@angular/common/http';
import { User } from '../cabinet/user';
import { Product } from '../backet/product';
import { environment } from 'src/environments/environment';
import {AuthenticationService} from "../services/authentication-service";
import { BacketComponentService } from '../backet/backetService';
import { Observable, of } from 'rxjs';
// import { DemoComponent } from '../shared/okta/social';

//import { KeycloakService } from 'src/app/auth/keycloack/keycloack.service';


@Component({
  selector: 'app-main',
  templateUrl: './html.html',
 // styleUrls: ['./table.component.css'],
})
export class MainComponent  {
  per:number;
  photo: string;
  index:Product[]=[];
 

constructor(
            public sanitizer: DomSanitizer,
            public auth: AuthenticationService,
            public backet:BacketComponentService
             //private keycloak:KeycloakService
             //public oktaAuth:OktaAuthService,
           
            // public demo:DemoComponent,
             //private authService: AuthService
             ){
            
  }
  
  URL:string=environment.apiUrl;
  isAuthenticated: boolean;
  onLogout() {
  //  this.keycloak.logout();
    // localStorage.clear();
    // this.cookie.delete("AUTH_SESSION_ID");
    // this.cookie.deleteAll('/auth/realms/Angular/','localhost');
    //this.httpService.logout();
    //this.router.navigate(['/']);
  }



public getSantizeUrl(url : string) {
  return this.sanitizer.bypassSecurityTrustUrl(url);
}

// Role():boolean{
//   this.http.get(this.URL+'/user/cabinet').subscribe((data: User) => { this.email=data.email;})
//     return;
//  }

  async ngOnInit() {  
   
  
    this.backet.count=0;
   // this.per= <number>await this.resolveAfter2Seconds(20);
    console.log(`async result: ${this.per}`);
  // this.isAuthenticated = await this.oktaAuth.isAuthenticated();
  // this.authService.authState.subscribe((user)  => {
  //   this.user =  user;
  //   this.loggedIn =  (user != null);
  //   if (this.loggedIn && !this.isAuthenticated){
  //     this.email=user.email;
  //   this.photo= user.photoUrl;
  //   localStorage.setItem('u',JSON.stringify(this.user));
  //   }
  // });

  // Subscribe to authentication state changes
 // let x=( await this.oktaAuth.getUser()).name;
 //  this.oktaAuth.$authenticationState.subscribe(
 //    (isAuthenticated: boolean)  =>
 //      this.isAuthenticated = isAuthenticated,
 //  );
 // this.http.get(this.URL+'/user/cabinet').subscribe((data: User) => { this.email=data.email;});
}


// async getValueWithAsync() {
//      this.per= <number>await this.resolveAfter2Seconds(20);
//      console.log(`async result: ${this.per}`);
//    }
// resolveAfter2Seconds(x) {

//  return new Promise(r);
//  }


// signInWithGoogle(): void {
// this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);
//
// }

// signInWithFB( ):  void {
// this.authService.signIn( FacebookLoginProvider.PROVIDER_ID);
// }

// signOut( ):  void {
// this.authService.signOut();
// }

}

