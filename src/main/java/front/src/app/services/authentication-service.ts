import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {UsernameToken} from '../models/username-token';
import {AuthUser} from '../models/AuthUser';
import { Router} from '@angular/router';
import {RegisterUser} from '../models/RegisterUser';
import { SocialUser, AuthService } from 'angularx-social-login';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private socialUser: SocialUser;

  constructor(private http: HttpClient, private router: Router, private authService: AuthService) {}

  signIn(user: AuthUser) {
    this.http.post(environment.apiUrl+'/api/v1/auth/login' , user)
      .subscribe((resp: UsernameToken) => {
        // this.router.navigate(['profile']);
        localStorage.setItem('token', resp.token);
        localStorage.setItem('roles', resp.roles.toString());
        localStorage.setItem('username', resp.username);

      }, error => {
        alert('Неверный логин или пароль!');
      });
  }

  register(user: RegisterUser) {
    this.http.post(environment.apiUrl+'/api/v1/auth/registration' , user)
      .subscribe((resp: UsernameToken) => {
        // this.router.navigate(['profile']);
        localStorage.setItem('token', resp.token);
        localStorage.setItem('roles', resp.roles.toString());
        localStorage.setItem('username', resp.username);
        const nav = localStorage.getItem('login_navigate');
        if(nav == 'home/backet' ){
          localStorage.setItem('login_navigate', '');
          this.router.navigate(['home/backet']);
        } else {
          this.router.navigate(['']);
        }
      }, error => {
      alert('Пользователь с таким логином уже существует!');
    });
  }
  signInGoogle(): void {

    this.authService.authState.subscribe((socialUser) => {
      this.socialUser = socialUser;
      console.log(this.socialUser);
      this.post11();
// this.loggedIn = (socialUser != null);
// if (this.logIn && !this.isAuthenticated) {
// this.email = user.email;
// this.photo = user.photoUrl
// }
    });
  }

  post11() {
    this.http.post(environment.apiUrl+'/api/v1/auth/login_oauth2',
      {
        provider: this.socialUser.provider,
        id: this.socialUser.id,
        email: this.socialUser.email,
        name: this.socialUser.name,
        photoUrl: this.socialUser.photoUrl,
        firstName: this.socialUser.firstName,
        lastName: this.socialUser.lastName,
        authToken: this.socialUser.authToken,
        idToken: this.socialUser.idToken,
        authorizationCode: this.socialUser.authorizationCode
      }).subscribe((data: UsernameToken) => {
      localStorage.setItem('token', data.token);
      localStorage.setItem('roles', data.roles.toString());
      localStorage.setItem('username', data.username);

      if(localStorage.getItem('login_navigate') === 'home/backet'){
        localStorage.setItem('login_navigate', '');
        this.router.navigate(['home/backet']);
      } else {
        this.router.navigate(['']);
      }
    });
  }


  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('roles');
    localStorage.removeItem('username');
    localStorage.setItem('login_navigate', '');
    //localStorage.clear();
  }

  public get username(): string {
    return localStorage.getItem('username');
  }

  public get logIn(): boolean {
    return (localStorage.getItem('token') !== null);
  }

   isAdmin(): boolean {
    if(localStorage.getItem('roles') !== null){
      const roles = localStorage.getItem('roles').split(',');
      for (let i = 0; i < roles.length; i++) {
        if (roles[i] === 'ROLE_ADMIN') {
          return true;
        }
      }
    }
    return false;
  }

  isUser(): boolean {
    if(localStorage.getItem('roles') !== null){
      const roles = localStorage.getItem('roles').split(',');
      for (let i = 0; i < roles.length; i++) {
        if (roles[i] === 'ROLE_ADMIN' || roles[i]==='ROLE_USER') {
          return true;
        }
      }
    }
    return false;
  }


  testRequest() {
    this.http.get('http://localhost:9090/api/v1/admin/get_user/402881a77172fcb0017173091af20000', {
      headers: {'Authorization':'CShop_' + localStorage.getItem('token')}
    })
      .subscribe((resp: any) => {
        console.log(resp.id);
      });
  }

}

