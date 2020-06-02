import { BacketComponent } from './backet-component';
import { HttpClient } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { Injectable } from '@angular/core';
import {Product} from './product';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
  })
  export class BacketComponentService {

    count:number;
   products: Product[];
   product:Product;
   aaa:Product[];
   sum:number;
   index:number[]=[];
    private URL:string=environment.apiUrl+"/order/";


    constructor(private http:HttpClient,private toastr:ToastrService) {


    }

  //req: HttpRequest<any>
    getProduct(){
      this.http.get(this.URL+"backet").subscribe((data:Product[]) => this.products=data);

    }

    postProduct(){
    this.aaa=this.products;
      this.http.post(this.URL+"createEmployee",this.product).subscribe(()=>{
        this.getProduct();
            this.toastr.success('New user created!','Successful.');
      });
    }

  putUser(){
    this.http.put(this.URL+"edit/"+this.product.product_id,this.product, { withCredentials: true}).subscribe(()=>{
      this.getProduct();

          this.toastr.success('User edit', 'Successful.');

    });
  }

    deleteProduct(){

      this.http.delete(this.URL+"backet/"+this.product.product_id).subscribe(()=>{
        this.getProduct();


            this.toastr.success('User delete!', 'Successful.');

      });
      this.count--;
    }
  }
