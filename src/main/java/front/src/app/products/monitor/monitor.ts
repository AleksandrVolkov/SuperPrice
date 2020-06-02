import { Component } from '@angular/core';


import {NgxPaginationModule} from 'ngx-pagination';
import { HttpClient } from '@angular/common/http';

import { FormControl, FormGroup } from '@angular/forms';
//  import { KeycloakService } from 'src/app/auth/keycloack/keycloack.service';
import { BacketComponentService } from 'src/app/backet/backetService';
import { Product } from 'src/app/backet/product';
import { Page } from 'src/app/models/pagination';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { ReturnString } from 'src/app/parcer/return-string';
import { AuthenticationService } from 'src/app/services/authentication-service';




@Component({
    selector: 'monitor',
    templateUrl: '../pc.html',
    styleUrls: ['../pc.css']
})
export class MonitorComponent  {

  likes:boolean=false;
  index:Product[]=[];
    page:Page;
    buyTicketForm: FormGroup;
    name:string="";
    flag: Product;
    products:Product[];
    URL:string=environment.apiUrl;
    config: any;
    collection = { count: 60, data: [] };
    public searchString:string="";
    public text:string="Компьютер";
  var3: number=2;
    constructor(//private keycloakService: KeycloakService,
      private httpService: BacketComponentService,private http:HttpClient,
                  private  toastr:ToastrService,public auth:AuthenticationService) {

       this._createForm();
       this.httpService.product={
            product_id:0,
            name:"11",
            short_image:"11",
            price:0,
            short_description:"",
            shop:"",
            product_type:"",
            count1:0,
            pluslike:0,
            dislike:0,
            
        }

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
        this.http.get(this.URL+'/mon?page=0').subscribe((data:Page) => { this.products=data.content
        this.config = {
            itemsPerPage: 10,
            currentPage: 1,
            totalItems: (data.totalPages)*10
          };})
    }

    pageChanged(event){
        this.config.currentPage = event;
    }

    change(){
      this.http.get(this.URL+'/mon?page='+(this.config.currentPage-1))
      .subscribe((data:Page) => { this.products=data.content});
    }

    private _createForm() {
        this.buyTicketForm = new FormGroup({
          passenger: new FormControl()
        })
      }

      populateForm(pd:any) {

       }

       Delete(pd:any){
         if(confirm("Delete Product ?"))
         {
           //this.populateForm(pd);
           this.httpService.product.product_id= pd.id;
           this.httpService.deleteProduct();
         }
       }

       addInBacket(item: Product) {
        if(confirm("Add Product ?"))
            {
              if(localStorage.getItem('array')==null){
                //localStorage.setItem('array',JSON.stringify(this.index));
                this.index.push(item);
                localStorage.setItem('array',JSON.stringify(this.index));
                 this.toastr.success( 'Для покупки нажмите на корзину!','Продукт добавлен в корзину');
            }else{
              this.index=JSON.parse(localStorage.getItem('array'));
              if(this.check(this.index,item)){
                this.toastr.warning('Продукт уже добавлен в корзину','Продукт добавлен в корзину')
              }else{
              
                this.index.push(item);
                localStorage.setItem('array',JSON.stringify(this.index));
                this.toastr.success( 'Для покупки нажмите на корзину!','Продукт добавлен в корзину');
              
              }
            }
            this.httpService.count++;
      }}
    
      check(array:Product[],item:Product):boolean{
    
        for(let i=0;i<array.length;i++){
          if(array[i].product_id === item.product_id){
            return true;
          }
      }
      return false;
    
      }


      public like(item:Product): void {
        if(this.auth.isUser()){
          item.pluslike++;
    
          this.http.post(this.URL+'/mon/like',item).subscribe((data:ReturnString)=>{
          if(data.disabledStop==true){
            item.pluslike--;
            alert("Вы уже оценили этот товар");
          }
        });
    }
    else{
      alert("Войдите для оценивания");
    }
    }
  
    public dislike(item:Product): void {
        if(this.auth.isUser()){
        item.dislike++;

        this.http.post(this.URL+'/mon/dislike',item).subscribe((data:ReturnString)=>{
        if(data.disabledStop==true){
          item.dislike--;
          alert("Вы уже оценили этот товар");
        }
      });
    }
    else{
      alert("Войдите для оценивания");
    }
  }

  public action(item:any){
    
    switch(item.srcElement.textContent){
      case "Монитор":
        this.var3=2;
        this.text="Монитор";
        console.log(this.var3);
        break;
      case "Компьютер":
          this.var3=1;
          this.text="Компьютер";
          console.log(this.var3);
          break;
      case "Принтер":
           this.text="Принтер";
            this.var3=3;
            console.log(this.var3);
            break;
      }
    }
  
    public search(item:any){
      if(this.searchString.includes('[')){
        this.searchString=this.searchString.split("[")[0];
       }
      switch (this.var3){
        case 1:
          this.http.get(this.URL+'/pc/'+this.searchString+'?page=0').subscribe((data:Page) => { this.products=data.content
          this.config = {
              itemsPerPage: 10,
              currentPage: 1,
              totalItems: (data.totalPages)*10
            };})
            break;
        case 2:
          this.http.get(this.URL+'/mon/'+this.searchString+'?page=0').subscribe((data:Page) => { this.products=data.content
          this.config = {
              itemsPerPage: 10,
              currentPage: 1,
              totalItems: (data.totalPages)*10
            };})
            break;
        case 3:
          this.http.get(this.URL+'/printer/'+this.searchString+'?page=0').subscribe((data:Page) => { this.products=data.content
          this.config = {
              itemsPerPage: 10,
              currentPage: 1,
              totalItems: (data.totalPages)*10
            };})
            break;
              
        }
    }
}
