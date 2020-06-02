import {BrowserModule} from '@angular/platform-browser';
import {NgModule, APP_INITIALIZER} from '@angular/core';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ToastrModule, ToastrService} from 'ngx-toastr';
// import {LoginComponent} from './login/login.component';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';

//import { httpInterceptorProviders } from './auth/auth-interceptor';


import {from} from 'rxjs';

import {CookieService} from 'ngx-cookie-service';
import {FileUploadModule} from 'ng2-file-upload';
import {CabinetComponent} from './cabinet/capinet.component';
import {NgxPaginationModule} from 'ngx-pagination';
import {BacketComponent} from './backet/backet-component';
// import {AdminComponent} from './admin/admincabinet/admincabinet';
import {AdminPanelComponent} from './admin/admin';
import {OrderComponent} from './admin/orders/orders';
import {GraphicComponent} from './admin/graphic/graphic';
import {TimetableComponent} from './admin/timetable/timetable';
import {MatButtonModule} from '@angular/material/button';
import {MatSelectModule} from '@angular/material/select';
import {FiltredProductsComponent} from './filtred-products/filtred-products.component';
import {PcFilterComponent} from './filtred-products/pc-filter/pc-filter.component';
import {MonitorFilterComponent} from './filtred-products/monitor-filter/monitor-filter.component';
import {PrinterFilterComponent} from './filtred-products/printer-filter/printer-filter.component';
import {MatInputModule} from '@angular/material/input';
import {MonitorComponent} from './products/monitor/monitor';
import {PcComponent} from './products/pc';

import {ParcerComponent} from './parcer/parcer.component';
import {PrinterComponent} from './products/printer/printer';
import {AuthServiceConfig, GoogleLoginProvider, SocialLoginModule} from 'angularx-social-login';
import {
  MatCheckboxModule,
  MatNativeDateModule,
  MatDatepickerModule,
  MatTabsModule,
  MatExpansionModule,
  MatIconModule,
  MatBadgeModule,
  MatListModule,
  MatProgressSpinnerModule
} from '@angular/material';
// import {DemoComponent} from './shared/okta/social';
import {HomepageComponent} from './homepage/homepage.component';
import {registerLocaleData, DatePipe} from '@angular/common';
import {RegisterComponent} from './register/register.component';
import {LoginComponent} from './login/login.component';
//import {HttpInterceptorService} from "./services/http-interceptor-service";
import {AuthInterceptor} from './shared/okta/auth.interceptor';
import {UsersManagerComponent} from './admin/users-manager/users-manager.component';
import {UploadFilesComponent} from './file/upload.component';
import {CommentComponent} from './products/comment/comment.component';
import {MainComponent} from './main/ts';


let config = new AuthServiceConfig([
  {
    id: GoogleLoginProvider.PROVIDER_ID,
    provider: new GoogleLoginProvider('207549790923-3qpu2choj254ov648bdtlktbfm97cerq.apps.googleusercontent.com')
  }

]);

export function provideConfig() {
  return config;
}

// export function kcFactory(keycloakService: KeycloakService) {
//   return () => keycloakService.init();
// }

@NgModule({
  declarations: [
    AppComponent,
    CabinetComponent,
    BacketComponent,
    // AdminComponent,
    AdminPanelComponent,
    OrderComponent,
    GraphicComponent,
    FiltredProductsComponent,
    PcFilterComponent,
    MonitorFilterComponent,
    PrinterFilterComponent,
    MonitorComponent,
    PcComponent,
    ParcerComponent,
    PrinterComponent,
    HomepageComponent,
    TimetableComponent,
    LoginComponent,
    RegisterComponent,
    UsersManagerComponent,
    UploadFilesComponent,
    CommentComponent, MainComponent
  ],
  imports: [
    SocialLoginModule,
    MatTabsModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FileUploadModule,
    NgxPaginationModule,
    MatButtonModule,
    MatSelectModule,
    MatInputModule,
    FormsModule,
    MatDatepickerModule,
    MatExpansionModule,

    MatBadgeModule,
    MatIconModule,
    MatListModule,
    MatProgressSpinnerModule,

    // OktaAuthModule.initAuth({
    //   issuer: 'https://dev-941847.okta.com/oauth2/default',
    //   redirectUri: window.location.origin + '/implicit/callback',
    //   clientId: '0oa2oj06ixL3Bzqbp4x6'

    // }),

    ToastrModule.forRoot({

      timeOut: 4000,
      positionClass: 'toast-top-right',
      preventDuplicates: false,
      progressBar: true
    }),
    FormsModule,


    MatCheckboxModule,
  ],
  providers: [//httpInterceptorProviders,

    ToastrService,
    //HttpInterceptorService,
    CookieService,
    // DemoComponent,
    DatePipe,
    // {provide: OKTA_CONFIG, useValue: oktaConfig},
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
    {
      provide: AuthServiceConfig,
      useFactory: provideConfig
    },
    // { provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: { appearance: 'fill' } }
    // {
    //   provide: APP_INITIALIZER,
    //   useFactory: kcFactory,
    //   deps: [KeycloakService],
    //   multi: true,

    // }

  ],
  //entryComponents: [GraphicComponent],
  bootstrap: [AppComponent]
})
export class AppModule {
}
