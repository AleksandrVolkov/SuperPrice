import { Component } from '@angular/core';


import {NgxPaginationModule} from 'ngx-pagination';
import { HttpClient } from '@angular/common/http';
//import { KeycloakService } from '../auth/keycloack/keycloack.service';
import { FormControl, FormGroup } from '@angular/forms';
import { BacketComponentService } from './backetService';
import {Product} from './product';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'angularx-social-login';
import { AuthenticationService } from '../services/authentication-service';
import { Router } from '@angular/router';



@Component({
    selector: 'backet',
    templateUrl: './backet-component.html',
    styleUrls: ['./backet.css']
})
export class BacketComponent  {

    buyTicketForm: FormGroup;
    name = '';
    flag: Product;
    products: Product[];
    private URL:string=environment.apiUrl+"/order/";
    summPrice: number;
    config: any;
    isWait:boolean=false;
    collection = { count: 60, data: [] };


    constructor(//private keycloakService: KeycloakService,
                  public httpService: BacketComponentService,
                  private http: HttpClient,private toastr:ToastrService,public auth:AuthenticationService,private router: Router) {

       this._createForm();
       this.httpService.product = {
        product_id: 0,
        name: '11',
        short_image: '11',
        price: 0,
        short_description: '',
        shop: '',
        product_type: '',
        count1:0,
        pluslike:0,
        dislike:0,
        
        };
        this.httpService.sum=0;

    }
    // ngOnChanges(): void {
    //       this.buyTicketForm.get('passenger').valueChanges.subscribe(v => {
    //           if(v.length==0){
    //             this.http.get('http://localhost:9090/order/backet').subscribe((data:Product[]) => { this.products=data
    //             this.config = {
    //                 itemsPerPage: 9,
    //                 currentPage: 1,
    //                 totalItems: data.length
    //               };})
    //           }
    //           else{
    //             this.http.get('http://localhost:9090/order/backet').subscribe((data:Product[]) => { this.products=data
    //             this.config = {
    //                 itemsPerPage: 9,
    //                 currentPage: 1,
    //                 totalItems: data.length
    //               };})
    //       }
    //     })

    // }
    public ngOnInit(): void {
      this.isWait=true;
      // for(let i=0;i<this.httpService.index.length;i++){
      //   this.httpService.products[i]=JSON.parse(localStorage.getItem(this.httpService.index[i].toString()));
      //   this.httpService.sum+=this.httpService.products[i].price;}
      this.httpService.products=JSON.parse(localStorage.getItem('array'));
      for(let i=0;i<this.httpService.products.length;i++){
        this.httpService.sum+=this.httpService.products[i].price*this.httpService.products[i].count1;
      }
      this.config = {
                itemsPerPage: 9,
                currentPage: 1,
                totalItems: this.httpService.products.length};

                this.isWait=false;
    }

    // getSummPrice() {
    //   this.summPrice = 0;
    //   for (let item of this.httpService.products) {
    //     this.summPrice += item.price;
    //   }
    // }

    pageChanged(event) {
        this.config.currentPage = event;
    }

    private _createForm() {
        this.buyTicketForm = new FormGroup({
          passenger: new FormControl()
        });
      }

      populateForm(pd: any) {

       }

       Delete(pd: Product) {
         if (confirm('Delete Product ?')) {
           this.httpService.sum-=pd.price*pd.count1;
           this.httpService.products=this.httpService.products.filter(x=>x.product_id!=pd.product_id);
           localStorage.setItem('array',JSON.stringify(this.httpService.products));
          // //this.http.delete(this.URL+"backet/"+pd.product_id).subscribe(()=>{
          //  // this.httpService.getProduct();


          //       this.toastr.success('User delete!', 'Successful.');

          // });
         }

       }

       addOrder() {
         if(this.auth.isUser()){
            this.isWait=true;
            this.http.post(this.URL+"add", this.httpService.products).subscribe(()=>{
              localStorage.removeItem('array');
              this.httpService.products=[];
              this.httpService.sum=0;
              this.isWait=false;
              this.toastr.success( 'Проверьте почту!','Заказ оформлен!');
            });
      }
      else{
        localStorage.setItem('login_navigate', 'home/backet');
        this.router.navigate(['home/login']);
      }


    }

    Add(item:Product){
      if(item.count1==1 || item.count1 < 100){
        this.httpService.products=JSON.parse(localStorage.getItem('array'));
        let x=this.httpService.products;
        x.find(x=>x.product_id==item.product_id).count1++;

        localStorage.setItem('array',JSON.stringify(x));
       // item.price+=item.price;
        this.httpService.sum+=item.price;
      }

        else return;

    }

    Minus(item:Product){
      this.httpService.products=JSON.parse(localStorage.getItem('array'));
        let x=this.httpService.products;
      if(x.find(x=>x.product_id==item.product_id).count1 == 1)
        return
        else {

        x.find(x=>x.product_id==item.product_id).count1--;
        localStorage.setItem('array',JSON.stringify(x));
        this.httpService.sum-=item.price;
        }

    }


}
