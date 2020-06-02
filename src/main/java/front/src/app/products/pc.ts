import { Component } from '@angular/core';


import { HttpClient } from '@angular/common/http';

import { FormControl, FormGroup } from '@angular/forms';
import { Product } from '../backet/product';
import { BacketComponentService } from '../backet/backetService';
import { Page } from '../models/pagination';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { ReturnString } from '../parcer/return-string';
import { AuthenticationService } from '../services/authentication-service';
import { IfStmt } from '@angular/compiler';


@Component({
    selector: 'pc',
    templateUrl: './pc.html',
    styleUrls: ['./pc.css']
})
export class PcComponent  {

  likes:boolean=false;
  dis:boolean=false;
 public searchString:string="";
  public text:string="Компьютер";
    var3:number=1;
    page:Page;
    buyTicketForm: FormGroup;
    name:string="";
    flag: Product;
    products:Product[];
    URL:string=environment.apiUrl;
    config: any;
    collection = { count: 60, data: [] };
    index:Product[]=[];
    badg:number=0;
    constructor(private httpService: BacketComponentService,private http:HttpClient,private toastr:ToastrService,public auth:AuthenticationService) {

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
        this.httpService.count=0;

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
        this.http.get(this.URL+'/pc?page=0').subscribe((data:Page) => { this.products=data.content
        this.config = {
            itemsPerPage: 10,
            currentPage: 1,
            totalItems: (data.totalPages)*10
          };})
    }

    pageChanged(event){
        this.config.currentPage = event-1;
        this.http.get(this.URL+'/pc/'+this.searchString+'?page=0'+this.config.currentPage)
                 .subscribe((data:Page) => { this.products=data.content});
    }

    change(){
      switch (this.var3){
        case 1: this.http.get(this.URL+'/pc/'+this.searchString+'?page='+(this.config.currentPage-1))
                .subscribe((data:Page) => { this.products=data.content});
                break;
        case 2:this.http.get(this.URL+'/mon/'+this.searchString+'?page='+(this.config.currentPage-1))
                .subscribe((data:Page) => { this.products=data.content});
                break;
        case 2:this.http.get(this.URL+'/printer/'+this.searchString+'?page='+(this.config.currentPage-1))
                        .subscribe((data:Page) => { this.products=data.content});
                        break;
    }
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
                this.httpService.count=this.index.length;
                 this.toastr.success( 'Для покупки нажмите на корзину!','Продукт добавлен в корзину');
            }else{
              this.index=JSON.parse(localStorage.getItem('array'));
              if(this.check(this.index,item)){
                this.toastr.warning('Продукт уже добавлен в корзину','Продукт добавлен в корзину')
              }else{
              
                this.index.push(item);
                this.httpService.count=this.index.length;
                localStorage.setItem('array',JSON.stringify(this.index));
                this.toastr.success( 'Для покупки нажмите на корзину!','Продукт добавлен в корзину');
              
              }
            }
            
      }
    }
    
      check(array:Product[],item:Product):boolean{
    
        for(let i=0;i<array.length;i++){
            if(array[i].product_id === item.product_id){
              return true;
            }
        }
        return false;
    
      }

          //localStorage.setItem(item.product_id.toString(),JSON.stringify(item));


       //  this.http.post(this.URL+'/order/backet/add',item).subscribe();//()=>{
        //   this.http.get('http://localhost:9090/order/backet').subscribe((data:Product[]) => { this.products=data
        //   this.config = {
        //       itemsPerPage: 9,
        //       currentPage: 1,
        //       totalItems: data.length
        //     };})

        // });
   

    public like(item:Product): void {
      if(this.auth.isUser()){
      var x=item.pluslike++;
      this.http.post(this.URL+'/pc/like',item).subscribe((data:ReturnString)=>{
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
    var x=item.dislike++;
    this.http.post(this.URL+'/pc/dislike',item).subscribe((data:ReturnString)=>{
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
//
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

  orderByPrice(){
    
  }
}
