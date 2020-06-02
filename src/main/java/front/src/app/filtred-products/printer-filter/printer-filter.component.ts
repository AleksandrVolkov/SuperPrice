import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {PrinterFilter} from '../../filters/printer-filter';
import {HttpClient} from '@angular/common/http';
import { environment } from 'src/environments/environment';
import {MonitorFilter} from "../../filters/monitor-filter";

@Component({
  selector: 'app-printer-filter',
  templateUrl: './printer-filter.component.html',
  styleUrls: ['./printer-filter.component.css']
})
export class PrinterFilterComponent implements OnInit {
  initFilter: PrinterFilter = {
    printerType: [],
    color: [],
    format: [],
    maxSpeed: '',
    minSpeed: '',
    twoSidedPrinting: [],
    minPrice: '',
    maxPrice: '',
    type: 'printerPost'
  };
  selectedFilter: PrinterFilter = {
    printerType: [],
    color: [],
    format: [],
    maxSpeed: '',
    minSpeed: '',
    twoSidedPrinting: [],
    minPrice: '',
    maxPrice: '',
    type: 'printerPost'
  };

  printerTypeFlag = false;
  colorFlag = false;
  formatFlag = false;
  twoSidedPrintingFlag = false;

  @Output()
  onSubmit: EventEmitter<PrinterFilter> = new EventEmitter<PrinterFilter>();

  URL:string=environment.apiUrl;
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get(this.URL+'/printer/minPrice').subscribe((data: string) => { this.initFilter.minPrice = data; });
    this.http.get(this.URL+'/printer/maxPrice').subscribe((data: string) => { this.initFilter.maxPrice = data; });
    this.http.get(this.URL+'/printer/format').subscribe((data: string[]) => { this.initFilter.format = data; });
    this.http.get(this.URL+'/printer/maxPrintSpeed').subscribe((data: string) => { this.initFilter.maxSpeed = data; });
    this.http.get(this.URL+'/printer/minPrintSpeed').subscribe((data: string) => { this.initFilter.minSpeed = data; });
    this.http.get(this.URL+'/printer/color').subscribe((data: string[]) => { this.initFilter.color = data; });
    this.http.get(this.URL+'/printer/type').subscribe((data: string[]) => { this.initFilter.printerType = data; });
    this.http.get(this.URL+'/printer/twoSidedPrinting').subscribe((data: string[]) =>
    { this.initFilter.twoSidedPrinting = data; });
  }

  getFiltredPrinterProducts() {
    this.checkInputs();
    this.selectedFilter.minPrice = this.selectedFilter.minPrice.toString();
    this.selectedFilter.maxPrice = this.selectedFilter.maxPrice.toString();
    this.selectedFilter.minSpeed = this.selectedFilter.minSpeed.toString();
    this.selectedFilter.maxSpeed = this.selectedFilter.maxSpeed.toString();


    const filter: PrinterFilter = {
      printerType: this.selectedFilter.printerType,
      color: this.selectedFilter.color,
      format: this.selectedFilter.format,
      maxSpeed: this.selectedFilter.maxSpeed,
      minSpeed: this.selectedFilter.minSpeed,
      twoSidedPrinting: this.selectedFilter.twoSidedPrinting,
      minPrice: this.selectedFilter.minPrice,
      maxPrice: this.selectedFilter.maxPrice,
      type: 'printerPost'
    };
    
    this.onSubmit.emit(filter);

    //

    if(this.printerTypeFlag){
      this.printerTypeFlag = false;
      this.selectedFilter.printerType = [];
    }
    if(this.colorFlag){
      this.colorFlag = false;
      this.selectedFilter.color = [];
    }
    if(this.formatFlag){
      this.formatFlag = false;
      this.selectedFilter.format = [];
    }
    if(this.twoSidedPrintingFlag){
      this.twoSidedPrintingFlag = false;
      this.selectedFilter.twoSidedPrinting = [];
    }
  }

  checkInputs() {
    if (this.selectedFilter.printerType.length === 0) {
      this.selectedFilter.printerType = this.initFilter.printerType;
      this.printerTypeFlag = true;
    }
    if (this.selectedFilter.color.length === 0) {
      this.selectedFilter.color = this.initFilter.color;
      this.colorFlag = true;
    }
    if (this.selectedFilter.format.length === 0) {
      this.selectedFilter.format = this.initFilter.format;
      this.formatFlag = true;
    }
    if (this.selectedFilter.maxSpeed === '') {
      this.selectedFilter.maxSpeed = this.initFilter.maxSpeed;
    }
    if (this.selectedFilter.minSpeed === '') {
      this.selectedFilter.minSpeed = this.initFilter.minSpeed;
    }
    if (this.selectedFilter.twoSidedPrinting.length === 0) {
      this.selectedFilter.twoSidedPrinting = this.initFilter.twoSidedPrinting;
      this.twoSidedPrintingFlag = true;
    }
    if (this.selectedFilter.minPrice === '') {
      this.selectedFilter.minPrice = this.initFilter.minPrice;
    }
    if (this.selectedFilter.maxPrice === '') {
      this.selectedFilter.maxPrice = this.initFilter.maxPrice;
    }
  }

  printerTypeSelectionChange(type) {
    let isInArr = 0;
    for (let i = 0; i < this.selectedFilter.printerType.length; i++) {
      if (type === this.selectedFilter.printerType[i]) {
        isInArr++;
        this.selectedFilter.printerType.splice(i, 1);
      }
    }
    if (isInArr === 0) {
      this.selectedFilter.printerType.push(type);
    }
    console.log(this.selectedFilter.printerType);
  }

  colorSelectionChange(color) {
    let isInArr = 0;
    for (let i = 0; i < this.selectedFilter.color.length; i++) {
      if (color === this.selectedFilter.color[i]) {
        isInArr++;
        this.selectedFilter.color.splice(i, 1);
      }
    }
    if (isInArr === 0) {
      this.selectedFilter.color.push(color);
    }
    console.log(this.selectedFilter.color);
  }

  formatSelectionChange(format) {
    let isInArr = 0;
    for (let i = 0; i < this.selectedFilter.format.length; i++) {
      if (format === this.selectedFilter.format[i]) {
        isInArr++;
        this.selectedFilter.format.splice(i, 1);
      }
    }
    if (isInArr === 0) {
      this.selectedFilter.format.push(format);
    }
    console.log(this.selectedFilter.format);
  }

  twoSidedPrintingSelectionChange(side) {
    let isInArr = 0;
    for (let i = 0; i < this.selectedFilter.twoSidedPrinting.length; i++) {
      if (side === this.selectedFilter.twoSidedPrinting[i]) {
        isInArr++;
        this.selectedFilter.twoSidedPrinting.splice(i, 1);
      }
    }
    if (isInArr === 0) {
      this.selectedFilter.twoSidedPrinting.push(side);
    }
    console.log(this.selectedFilter.twoSidedPrinting);
  }
}
