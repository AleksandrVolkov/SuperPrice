import {Component, OnInit, OnChanges, SimpleChanges, Input, Output, EventEmitter} from '@angular/core';

import {NgxPaginationModule} from 'ngx-pagination';
import {HttpClient} from '@angular/common/http';
import {FormGroup, FormControl} from '@angular/forms';
//import { KeycloakService } from 'src/app/auth/keycloack/keycloack.service';
import {Order} from './order';
import { environment } from 'src/environments/environment';
import {AuthenticationService} from "../../services/authentication-service";
import { MatDatepickerInputEvent } from '@angular/material';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'orders',
  templateUrl: './orders.html',
  styleUrls: ['./orders.css']
})
export class OrderComponent implements OnInit {
  //buyTicketForm: FormGroup;
  name: string = '';
  flag: Order;
  // contracts: Contract[];
  flags: Order[];
  minDate: Date;
  maxDate: Date;
  myDate:Date;
  myDate1:Date;

  config: any;
  collection = {count: 60, data: []};
  count: any[];
  date: any[];

  constructor(private http: HttpClient,private datePipe: DatePipe, public auth: AuthenticationService) {

    this.http.get(environment.apiUrl+'/order/mindate').subscribe((data:Date)=>this.minDate=data)
    this.http.get(environment.apiUrl+'/order/maxdate').subscribe((data:Date)=>this.maxDate=data)
    this.myDate=this.minDate;
    this.myDate1=this.maxDate;
    
  }


  @Output()
  dateChange:EventEmitter<MatDatepickerInputEvent<any>>;
  // ngOnInit(): void {
  //   this.date=[];
  //   this.count=[];
  //   this.myDate == null ? this.myDate=this.maxDate : this.myDate;
  //   this.myDate1 == null ? this.myDate1=this.maxDate : this.myDate1;
  // }
  

  someMethodName($event){

    console.log(this.datePipe.transform(this.myDate,"yyyy-MM-dd"))
     // if (value.length == 0) {
        this.http.get(environment.apiUrl+'/order/allorders?date='+this.datePipe.transform(this.myDate,"yyyy-MM-dd")).subscribe((data: Order[]) => {
          this.flags = data;
          this.config = {
            itemsPerPage: 9,
            currentPage: 1,
            totalItems: data.length
          };
        });
  }
 
  ngOnChanges(): void {

   // this.buyTicketForm.get('passenger').valueChanges.subscribe(value => {
      //  let x=Date.parse( value+" 16:53:34")/1000;
      console.log(this.datePipe.transform(this.myDate,"yyyy-MM-dd"))
     // if (value.length == 0) {
        this.http.get(environment.apiUrl+'/order/allorders?date='+this.datePipe.transform(this.myDate,"yyyy-MM-dd")).subscribe((data: Order[]) => {
          this.flags = data;
          this.config = {
            itemsPerPage: 9,
            currentPage: 1,
            totalItems: data.length
          };
        });
    //   } else {
    //     this.http.get(environment.apiUrl+'/order/allorders?date=' + value).subscribe((data: Order[]) => {
    //       this.flags = data;
    //       // for(let i=0;i<this.flags.length;i++){
    //       //   this.flags[i].date=  new Date(this.flags[i].createdTimestamp);
    //       // }
    //       this.config = {
    //         itemsPerPage: 9,
    //         currentPage: 1,
    //         totalItems: data.length
    //       };
    //     });
    //   }
    // });

  }

  public ngOnInit(): void {
    this.http.get(environment.apiUrl+'/order/mindate').subscribe((data:Date)=>this.minDate=data)
    this.http.get(environment.apiUrl+'/order/maxdate').subscribe((data:Date)=>this.maxDate=data)
  //   this.myDate=this.minDate;
  //   this.myDate1=this.maxDate;
  //   this.myDate == null ? this.myDate=this.maxDate : this.myDate;
  //  this.myDate1 == null ? this.myDate1=this.maxDate : this.myDate1;
    console.log(this.http.get(environment.apiUrl+'/order/mindate'))
    this.http.get(environment.apiUrl+'/order/allorders?date='+null).subscribe((data: Order[]) => {
      this.flags = data;
      // for(let i=0;i<this.flags.length;i++){
      //   this.flags[i].orderDate=  new Date(this.flags[i].orderDate);
      // }
      this.config = {
        itemsPerPage: 9,
        currentPage: 1,
        totalItems: data.length
      };
    });
  }

  pageChanged(event) {
    this.config.currentPage = event;
  }

  // // private _createForm() {
  // //   this.buyTicketForm = new FormGroup({
  // //     passenger: new FormControl(null)
  // //   });
  // // }


}
