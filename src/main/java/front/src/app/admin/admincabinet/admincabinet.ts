// import {Component, OnInit, OnChanges, SimpleChanges, Input} from '@angular/core';
//
// import {User} from './user';
//
//
// import {NgxPaginationModule} from 'ngx-pagination';
// import {HttpClient} from '@angular/common/http';
// import {FormGroup, FormControl} from '@angular/forms';
// import { environment } from 'src/environments/environment';
// import {AuthenticationService} from "../../services/authentication-service";
//
// // import { KeycloakService } from 'src/app/auth/keycloack/keycloack.service';
//
// @Component({
//   selector: 'admin',
//   templateUrl: './admincabinet.html',
//   styleUrls: ['./admincabinet.css']
// })
// export class AdminComponent implements OnInit, OnChanges {
//
//   buyTicketForm: FormGroup;
//   name = '';
//   flag: User;
//   // contracts: Contract[];
//   flags: User[];
//
//   config: any;
//   collection = {count: 60, data: []};
//
//   constructor(private http: HttpClient, public auth: AuthenticationService) {
//
//     this._createForm();
//   }
//
//
//   ngOnChanges(): void {
//
//
//     this.buyTicketForm.get('passenger').valueChanges.subscribe(value => {
//       //  let x=Date.parse( value+" 16:53:34")/1000;
//
//       if (value.length == 0) {
//         this.http.get(environment.apiUrl+'/user/users').subscribe((data: User[]) => {
//           this.flags = data;
//           this.config = {
//             itemsPerPage: 9,
//             currentPage: 1,
//             totalItems: data.length
//           };
//         });
//       } else {
//         this.http.get(environment.apiUrl+'/user/users/' + value).subscribe((data: User[]) => {
//           this.flags = data;
//           for (let i = 0; i < this.flags.length; i++) {
//             this.flags[i].date = new Date(this.flags[i].createdTimestamp);
//           }
//           this.config = {
//             itemsPerPage: 9,
//             currentPage: 1,
//             totalItems: data.length
//           };
//         });
//       }
//     });
//
//   }
//
//   public ngOnInit(): void {
//     this.http.get(environment.apiUrl+'/user/users').subscribe((data: User[]) => {
//       this.flags = data;
//       for (let i = 0; i < this.flags.length; i++) {
//         this.flags[i].date = new Date(this.flags[i].createdTimestamp);
//       }
//       this.config = {
//         itemsPerPage: 9,
//         currentPage: 1,
//         totalItems: data.length
//       };
//     });
//   }
//
//   pageChanged(event) {
//     this.config.currentPage = event;
//   }
//
//   private _createForm() {
//     this.buyTicketForm = new FormGroup({
//       passenger: new FormControl(null)
//     });
//   }
//
//
//   // public logout() {
//   //     this.keycloakService.logout();
//   // }
//
//   // Role():boolean{
//   //    let a= this.keycloakService.role();
//   //    return a;
//   //
// }
