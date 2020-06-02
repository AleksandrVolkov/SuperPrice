import { Component, OnInit } from '@angular/core';
import {PcFilter} from '../filters/pc-filter';
import {FormControl, FormGroup} from '@angular/forms';
import { Product } from '../backet/product';
// import {KeycloakService} from '../auth/keycloack/keycloack.service';
import {BacketComponentService} from '../backet/backetService';
import {HttpClient} from '@angular/common/http';
import {MonitorFilter} from '../filters/monitor-filter';
import {PrinterFilter} from '../filters/printer-filter';
import { Page } from '../models/pagination';
import { VirtualTimeScheduler } from 'rxjs';
import { CommonFilter } from '../filters/common-filter';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';




@Component({
  selector: 'app-filtred-products',
  templateUrl: './filtred-products.component.html',
  styleUrls: ['./filtred-products.component.css']
})
export class FiltredProductsComponent implements OnInit {
  alert = false;
  pcFilter = false;
  monitorFilter = false;
  printerFilter = false;
  visiblePagination = false;

  buyTicketForm: FormGroup;
  name = '';
  flag: Product;
  products: Product[];
  URL = environment.apiUrl;
  config: any;
  filter: CommonFilter;
  collection = { count: 60, data: [] };
  index: Product[]=[];
  constructor(// private keycloakService: KeycloakService,
     private httpService: BacketComponentService, private http: HttpClient, private toastr: ToastrService) {
    this._createForm();
    this.httpService.product = {
      product_id: 0,
      name: '11',
      short_image: '11',
      price: 0,
      short_description: '',
      shop: '',
      product_type: '',
      count1: 0,
      pluslike:0,
      dislike:0,
     
    };
  }

  ngOnInit() {
    this.pcFilter = true;
    this.products = [];
      this.config = {
        itemsPerPage: 10,
        currentPage: 1,
        totalItems: 10
      };
    // this.http.get(this.URL + '/pc?page=0').subscribe((data: Page) => { this.products = data.content;
    //   this.config = {
    //     itemsPerPage: 10,
    //     currentPage: 1,
    //     totalItems: data.totalPages * 10
    //   }; });
  }

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

  change() {
    if (this.pcFilter == true) {
      this.http.post(this.URL + '/pc/params?page=' + (this.config.currentPage - 1), this.filter).
      subscribe((data: Page) => { this.products = data.content , this.config = {
        itemsPerPage: 10,
        currentPage: this.config.currentPage,
        totalItems: data.totalPages * 10
      }; });
    } else if (this.monitorFilter == true) {
      this.http.post(this.URL + '/mon/params?page=' + (this.config.currentPage - 1), this.filter).
      subscribe((data: Page) => { this.products = data.content , this.config = {
        itemsPerPage: 10,
        currentPage: this.config.currentPage,
        totalItems: data.totalPages * 10
      }; });
    } else {
      this.http.post(this.URL + '/printer/params?page=' + (this.config.currentPage - 1), this.filter).
      subscribe((data: Page) => { this.products = data.content , this.config = {
        itemsPerPage: 10,
        currentPage: this.config.currentPage,
        totalItems: data.totalPages * 10
      }; });
    }
  }

  Delete(pd: any) {
    if (confirm('Delete Product ?')) {
      // this.populateForm(pd);
      this.httpService.product.product_id = pd.id;
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
  }}

  check(array:Product[],item:Product):boolean{

    for(let i=0;i<array.length;i++){
      if(array[i].product_id === item.product_id){
        return true;
      }
  }
  return false;

  }

  changeFilter(event) {
    this.visiblePagination = false;
    this.alert = false;
    if (event === 'pc') {
      this.pcFilter = true;
      this.monitorFilter = false;
      this.printerFilter = false;
      // this.http.get(this.URL+'/pc?page=0').subscribe((data: Page) => { this.products = data.content , this.config = {
      //   itemsPerPage: 10,
      //   currentPage: this.config.currentPage,
      //   totalItems: data.totalPages * 10
      // }; });
    } else if (event === 'monitor') {
      this.pcFilter = false;
      this.monitorFilter = true;
      this.printerFilter = false;
      // this.http.get(this.URL+'/mon?page=0').subscribe((data: Page) => { this.products = data.content , this.config = {
      //   itemsPerPage: 10,
      //   currentPage: this.config.currentPage,
      //   totalItems: data.totalPages * 10
      // }; });
    } else if (event === 'printer') {
      this.pcFilter = false;
      this.monitorFilter = false;
      this.printerFilter = true;
      // this.http.get(this.URL+'/printer?page=0').subscribe((data: Page) => { this.products = data.content , this.config = {
      //   itemsPerPage: 10,
      //   currentPage: this.config.currentPage,
      //   totalItems: data.totalPages * 10
      // }; });
    }
  }

  getFiltredPcProducts(filter: PcFilter) {
    this.filter = filter;
    this.http.post(this.URL + '/pc/params?page=0', filter).
    subscribe((data: Page) => {
      this.products = data.content ;
      if(this.products.length === 0){
        this.alert = true;
      } else {
        this.alert = false;
      }
      this.config = {
      itemsPerPage: 10,
      currentPage: 1,
      totalItems: data.totalPages * 10
    };
      this.visiblePagination = true;
    }, error => {
      this.alert = true;
    });
  }

  getFiltredMonitorProducts(filter: MonitorFilter) {

    this.filter = filter;
    this.http.post(this.URL + '/mon/params?page=0', filter).
    subscribe((data: Page) => {
      this.products = data.content ;
      if(this.products.length === 0){
        this.alert = true;
      } else {
        this.alert = false;
      }
      this.config = {
        itemsPerPage: 10,
        currentPage: 1,
        totalItems: data.totalPages * 10
      };
      this.visiblePagination = true;
    }, error => {
      this.alert = true;
    });
  }

  getFiltredPrinterProducts(filter: PrinterFilter) {
    this.filter = filter;
    this.http.post(this.URL + '/printer/params?page=0', filter).
    subscribe((data: Page) => {
      this.products = data.content;
      if(this.products.length === 0){
        this.alert = true;
      } else {
        this.alert = false;
      }
      this.config = {
      itemsPerPage: 10,
      currentPage: 1,
      totalItems: data.totalPages * 10
    };
      this.visiblePagination = true;
    }, error => {
      this.alert = true;
    });
  }
}
