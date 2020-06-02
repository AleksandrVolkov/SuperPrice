import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

//import { AuthGuard } from './auth/auth.guard';
import {CabinetComponent} from './cabinet/capinet.component';

import {BacketComponent} from './backet/backet-component';
import {PcComponent} from './products/pc';

import {AdminPanelComponent} from './admin/admin';
// import {AdminComponent} from './admin/admincabinet/admincabinet';
import {OrderComponent} from './admin/orders/orders';
import {GraphicComponent} from './admin/graphic/graphic';

import {FiltredProductsComponent} from './filtred-products/filtred-products.component';
import {MonitorComponent} from './products/monitor/monitor';
// import {OktaAuthGuard, OktaCallbackComponent} from '@okta/okta-angular';
import {ParcerComponent} from './parcer/parcer.component';
import {PrinterComponent} from './products/printer/printer';
import {HomepageComponent} from './homepage/homepage.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {UsersManagerComponent} from './admin/users-manager/users-manager.component';
import {MainComponent} from './main/ts';

const routes: Routes = [

  {path: '', component: HomepageComponent},
  {
    path: 'home', component: MainComponent,
    children: [
      {
        path: 'admin', component: AdminPanelComponent,
        children: [
          // {path: 'users', component: AdminComponent},
          {path: 'orders', component: OrderComponent},
          {path: 'graphic', component: GraphicComponent},
          {path: 'parcer', component: ParcerComponent},
          {path: 'users_manager', component: UsersManagerComponent}

        ]
      },
      {path: 'products', component: PcComponent},
      {path: 'filtred_products', component: FiltredProductsComponent},
      {path: 'login', component: LoginComponent},
      {path: 'register', component: RegisterComponent},

      {path: 'cabinet', component: CabinetComponent},
      {path: 'backet', component: BacketComponent},
      {path: 'monitor', component: MonitorComponent},
      {path: 'parcer', component: ParcerComponent},
      {path: 'printers', component: PrinterComponent},

    ]
  },
  // {path: 'home/flags', component: FlagComponent}//, canActivate: [AuthGuard]},
  // {path:"home/backet",component:BacketComponent,canActivate: [AuthGuard]},
  // {path:"home/products",component:ProductComponent},
  // {path:"home/admin",component:AdminComponent,canActivate: [AuthGuard]},
];

// const route: Routes = [
//  {
//     path: '', component:TableComponent,

//   },
//   { path:'add',component:AddComponentComponent
//   }
// ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
