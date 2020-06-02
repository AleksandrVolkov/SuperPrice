import {Component, OnInit} from '@angular/core';
import {AuthService, FacebookLoginProvider, GoogleLoginProvider, SocialUser} from "angularx-social-login";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {DomSanitizer} from "@angular/platform-browser";
// import {OktaAuthService} from "@okta/okta-angular";
import {HttpClient} from "@angular/common/http";
import {User} from "../cabinet/user";
import {AuthenticationService} from "../services/authentication-service";
import {UsernameToken} from "../models/username-token";
import { environment } from 'src/environments/environment';
import { BacketComponentService } from '../backet/backetService';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  private user: SocialUser;
  private loggedIn: boolean;
  private email: any;
  photo: string;

  constructor(
               public sanitizer: DomSanitizer,
              public auth: AuthenticationService,
              //private keycloak:KeycloakService
               public backet: BacketComponentService,
              private http: HttpClient,
              // public demo:DemoComponent,
              private authService: AuthService
  ) {

  }

  URL: string = environment.apiUrl;
  isAuthenticated: boolean;

  onLogout() {
    this.backet.products.length;
    //  this.keycloak.logout();
    // localStorage.clear();
    // this.cookie.delete("AUTH_SESSION_ID");
    // this.cookie.deleteAll('/auth/realms/Angular/','localhost');
    //this.httpService.logout();
    //this.router.navigate(['/']);
  }


  public getSantizeUrl(url: string) {
    return this.sanitizer.bypassSecurityTrustUrl(url);

  }

  Role(): boolean {
    this.http.get(this.URL + '/user/cabinet').subscribe((data: User) => {
      this.email = data.email;
    });
    return;
  }

  async ngOnInit() {
    this.authService.authState.subscribe((user) => {
      this.user = user;
      this.loggedIn = (user != null);
      if (this.loggedIn && !this.isAuthenticated) {
        this.email = user.email;
        this.photo = user.photoUrl;
        // this.http.post('http://localhost:9090/api/v1/auth/login_oauth2',
        //   {
        //     "provider": user.provider,
        //     "id": user.id,
        //     "email": user.email,
        //     "name": user.name,
        //     "photoUrl": user.photoUrl,
        //     "firstName": user.firstName,
        //     "lastName": user.lastName,
        //     "authToken": user.authToken,
        //     "idToken": user.idToken,
        //     "authorizationCode": user.authorizationCode
        //   }).subscribe((data: UsernameToken) => {
        //   localStorage.setItem('token', data.token);
        //   localStorage.setItem('roles', data.roles.toString());
        //   localStorage.setItem('username', data.username);
        // });
        // String provider;
        // String id;
        // String email;
        // String name;
        // String photoUrl;
        // String firstName;
        // String lastName;
        // String authToken;
        // String idToken;
        // String authorizationCode;

      }
    });
    //this.isAuthenticated = await this.oktaAuth.isAuthenticated();
    // Subscribe to authentication state changes
    // let x=( await this.oktaAuth.getUser()).name;
    // this.oktaAuth.$authenticationState.subscribe(
    //   (isAuthenticated: boolean) =>
    //     this.isAuthenticated = isAuthenticated,
    // );
    this.http.get(this.URL + '/user/cabinet').subscribe((data: User) => {
      this.email = data.email;
    });
  }


  signInWithGoogle(): void {
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);

    this.authService.authState.subscribe((user) => {
      this.user = user;
      this.loggedIn = (user != null);
      if (this.loggedIn && !this.isAuthenticated) {
        this.email = user.email;
        this.photo = user.photoUrl;
        this.http.post('http://localhost:9090/api/v1/auth/login_oauth2',
          {
            "provider": user.provider,
            "id": user.id,
            "email": user.email,
            "name": user.name,
            "photoUrl": user.photoUrl,
            "firstName": user.firstName,
            "lastName": user.lastName,
            "authToken": user.authToken,
            "idToken": user.idToken,
            "authorizationCode": user.authorizationCode
          }).subscribe((data: UsernameToken) => {
          localStorage.setItem('token', data.token);
          localStorage.setItem('roles', data.roles.toString());
          localStorage.setItem('username', data.username);
        });
      }
    });
  }

  // signInWithFB(): void {
  //   this.authService.signIn(FacebookLoginProvider.PROVIDER_ID);
  // }

  // signOut(): void {
  //   this.authService.signOut();
  // }
}
