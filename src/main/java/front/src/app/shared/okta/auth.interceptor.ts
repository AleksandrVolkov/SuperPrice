import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable, from } from 'rxjs';
import { OktaAuthService } from '@okta/okta-angular';
import { Injectable } from '@angular/core';
import { AuthService, SocialUser } from 'angularx-social-login';
//import { DemoComponent } from './social';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  

  constructor( private authService: AuthService) {
  }

  // intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
  //   return from(this.handleAccess(request, next));
  // }

  // private async handleAccess(request: HttpRequest<any>, next: HttpHandler): Promise<HttpEvent<any>> {
  //   // Only add an access token to whitelisted origins
  //   const allowedOrigins = ['http://localhost:4200'];
  //   if (allowedOrigins.some(url => request.urlWithParams.includes(url))) {
  //     const accessToken = await this.oktaAuth.getAccessToken();
  //     request = request.clone({
  //       setHeaders: {
  //         Authorization: 'Bearer ' + accessToken
  //       }
  //     });
  //   }
  //   return next.handle(request).toPromise();
  // }
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return from(this.handleAccess(request, next));
  }

  private async handleAccess(request: HttpRequest<any>, next: HttpHandler): Promise<HttpEvent<any>> {
    // Only add to known domains since we don't want to send your tokens to just anyone.
    // Also, Giphy's API fails when the request includes a token.
    // let c=this.oktaAuth.getUser();
    // let c1 =this.oktaAuth.getUser;
    // console.log(this.oktaAuth.getIdToken);
    // let obj=localStorage.getItem('okta-token-storage').toString();
    // let f=obj.split(',');
    // let f1;
    // for(let i=0;i<f.length;i++){
    //   if(f[i].startsWith('"idp"')){
    //       f1=f[i];
    //   }
    // }
    //c.
    //console.log(f1);
    //console.log(f);
    // const accessToken='';
    // //if (request.urlWithParams.indexOf('localhost') > -1) {
    //   this.authService.authState.subscribe((user)  => {
    //     this.user =  user;
    //     this.loggedIn =  (user != null);
    //     const accessToken = this.user.idToken;this.user.authToken
    //   });
   // return next.handle(request).toPromise();;
    const accessToken=  localStorage.getItem('token');
    if(accessToken ==null){
        return next.handle(request).toPromise();
    }
    // this.authService.authState.subscribe((user)  => {
    //   this.user =  user;
    //   this.loggedIn =  (user != null);const accessToken =  this.user.authToken;
    // });
    // this.user=JSON.parse(localStorage.getItem('u'));
    // accessToken=this.user.idToken;

    request = request.clone({
      setHeaders: {
        Authorization: 'CShop_' + accessToken
      }
    });
    // }
    return next.handle(request).toPromise();
         //localStorage.getItem('jwtToken')
//       const authReq = req.clone({
//         headers: new HttpHeaders({
//           // 'Content-Type': 'application/json',
//           'Authorization': `CShop_${localStorage.getItem('jwtToken')}`
//         })
//       });
    // const allowedOrigins = ['http://localhost'];
    // if (allowedOrigins.some(url => request.urlWithParams.includes(url))) {
    //   const accessToken = await this.oktaAuth.getAccessToken();
    //   request = request.clone({
    //     setHeaders: {
    //       Authorization: 'Bearer ' + accessToken
    //     }
    //   });
    // }
    // return next.handle(request).toPromise();
  }
  }

