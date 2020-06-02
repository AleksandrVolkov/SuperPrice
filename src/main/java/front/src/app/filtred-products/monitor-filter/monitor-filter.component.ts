import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MonitorFilter} from '../../filters/monitor-filter';
import {HttpClient} from '@angular/common/http';
import {toNumbers} from "@angular/compiler-cli/src/diagnostics/typescript_version";
import { environment } from 'src/environments/environment';
import {PcFilter} from "../../filters/pc-filter";

@Component({
  selector: 'app-monitor-filter',
  templateUrl: './monitor-filter.component.html',
  styleUrls: ['./monitor-filter.component.css']
})
export class MonitorFilterComponent implements OnInit {
  initFilter: MonitorFilter = {
    minScreen: '',
    maxScreen: '',
    minScreenFrequency: '',
    maxScreenFrequency: '',
    matrixType: [],
    screenResolution: [],
    minPrice: '',
    maxPrice: '',
    type: 'monitorPost'
  };
  selectedFilter: MonitorFilter = {
    minScreen: '',
    maxScreen: '',
    minScreenFrequency: '',
    maxScreenFrequency: '',
    matrixType: [],
    screenResolution: [],
    minPrice: '',
    maxPrice: '',
    type: 'monitorPost'
  };

  monitorSize: number[] = [];
  monitorSizeSelect: number[] = [];

  matrixTypeFlag = false;
  screenResolutionFlag = false;

  @Output()
  onSubmit: EventEmitter<MonitorFilter> = new EventEmitter<MonitorFilter>();
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get(environment.apiUrl+'/mon/minPrice').subscribe((data: string) => { this.initFilter.minPrice = data; });
    this.http.get(environment.apiUrl+'/mon/maxPrice').subscribe((data: string) => { this.initFilter.maxPrice = data; });
    this.http.get(environment.apiUrl+'/mon/matrixType').subscribe((data: string[]) => { this.initFilter.matrixType = data; });
    this.http.get(environment.apiUrl+'/mon/minScreen').subscribe((data: string) => { this.initFilter.minScreen = data; this.initScreenSize(); });
    this.http.get(environment.apiUrl+'/mon/maxScreen').subscribe((data: string) => { this.initFilter.maxScreen = data; this.initScreenSize(); });
    this.http.get(environment.apiUrl+'/mon/minFreq').subscribe((data: string) => { this.initFilter.minScreenFrequency = data; });
    this.http.get(environment.apiUrl+'/mon/maxFreq').subscribe((data: string) => { this.initFilter.maxScreenFrequency = data; });
    this.http.get(environment.apiUrl+'/mon/screenResolution').
    subscribe((data: string[]) => { this.initFilter.screenResolution = data; });

  }

  initScreenSize() {
    if (this.initFilter.maxScreen !== '' && this.initFilter.minScreen !== '') {
      //this.monitorSize.push(Number(this.initFilter.minScreen));
      for (let i = Math.ceil(Number(this.initFilter.minScreen)); i < Math.floor(Number(this.initFilter.maxScreen)); i++) {
        this.monitorSize.push(i);
      }
      this.monitorSize.push(Number(this.initFilter.maxScreen));
    }
  }


  getFiltredMonitorProducts() {
    this.checkInputs();
    this.selectedFilter.minPrice = this.selectedFilter.minPrice.toString();
    this.selectedFilter.maxPrice = this.selectedFilter.maxPrice.toString();
    this.selectedFilter.minScreen = this.selectedFilter.minScreen.toString();
    this.selectedFilter.maxScreen = this.selectedFilter.maxScreen.toString();
    this.selectedFilter.minScreenFrequency = this.selectedFilter.minScreenFrequency.toString();
    this.selectedFilter.maxScreenFrequency = this.selectedFilter.maxScreenFrequency.toString();


    const filter: MonitorFilter = {
      minScreen: this.selectedFilter.minScreen,
      maxScreen: this.selectedFilter.maxScreen,
      minScreenFrequency: this.selectedFilter.minScreenFrequency,
      maxScreenFrequency: this.selectedFilter.maxScreenFrequency,
      matrixType: this.selectedFilter.matrixType,
      screenResolution: this.selectedFilter.screenResolution,
      minPrice: this.selectedFilter.minPrice,
      maxPrice: this.selectedFilter.maxPrice,
      type: 'monitorPost'
    };
    this.onSubmit.emit(filter);

    //
    if(this.matrixTypeFlag){
      this.selectedFilter.matrixType = [];
      this.matrixTypeFlag = false;
    }

    if(this.screenResolutionFlag){
      this.selectedFilter.screenResolution = [];
      this.screenResolutionFlag = false;
    }
  }


  checkInputs() {
    if (this.selectedFilter.minPrice === '') {
      this.selectedFilter.minPrice = this.initFilter.minPrice;
    }

    if (this.selectedFilter.maxPrice === '') {
      this.selectedFilter.maxPrice = this.initFilter.maxPrice;
    }

    if (this.selectedFilter.matrixType.length === 0) {
      this.selectedFilter.matrixType = this.initFilter.matrixType;
      this.matrixTypeFlag = true;
    }

    if (this.selectedFilter.screenResolution.length === 0) {
      this.selectedFilter.screenResolution = this.initFilter.screenResolution;
      this.screenResolutionFlag = true;
    }

    if (this.monitorSizeSelect.length === 0) {
      this.selectedFilter.minScreen = this.initFilter.minScreen;
      this.selectedFilter.maxScreen = this.initFilter.maxScreen;

    } else {
      this.selectedFilter.minScreen = Math.min.apply(null, this.monitorSizeSelect);
      this.selectedFilter.maxScreen = Math.max.apply(null, this.monitorSizeSelect);
    }


    if (this.selectedFilter.minScreenFrequency === '') {
      this.selectedFilter.minScreenFrequency = this.initFilter.minScreenFrequency;
    }

    if (this.selectedFilter.maxScreenFrequency === '') {
      this.selectedFilter.maxScreenFrequency = this.initFilter.maxScreenFrequency;
    }
  }


  matrixTypeChangeSelect(type) {
    let isInArr = 0;
    for (let i = 0; i < this.selectedFilter.matrixType.length; i++) {
      if (type === this.selectedFilter.matrixType[i]) {
        isInArr++;
        this.selectedFilter.matrixType.splice(i, 1);
      }
    }
    if (isInArr === 0) {
      this.selectedFilter.matrixType.push(type);
    }
  }

  screenResolutionTypeChangeSelect(resolution) {
    let isInArr = 0;
    for (let i = 0; i < this.selectedFilter.screenResolution.length; i++) {
      if (resolution === this.selectedFilter.screenResolution[i]) {
        isInArr++;
        this.selectedFilter.screenResolution.splice(i, 1);
      }
    }
    if (isInArr === 0) {
      this.selectedFilter.screenResolution.push(resolution);
    }
  }

  screenSizeChangeSelect(size) {
    let isInArr = 0;
    for (let i = 0; i < this.monitorSizeSelect.length; i++) {
      if (size === this.monitorSizeSelect[i]) {
        isInArr++;
        this.monitorSizeSelect.splice(i, 1);
      }
    }
    if (isInArr === 0) {
      this.monitorSizeSelect.push(size);
    }
    console.log(this.monitorSizeSelect);
  }

}
