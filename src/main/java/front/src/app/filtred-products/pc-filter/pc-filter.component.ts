import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {PcFilter} from '../../filters/pc-filter';
import {HttpClient} from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-pc-filter',
  templateUrl: './pc-filter.component.html',
  styleUrls: ['./pc-filter.component.css']
})
export class PcFilterComponent implements OnInit {
  initFilter: PcFilter = {
    minPrice: '',
    maxPrice: '',
    ramSize: [],
    ramType: [],
    cores: [],
    minFreq: '',
    maxFreq: '',
    type: 'pcPost'
  };

  selectedFilter: PcFilter = {
    minPrice: '',
    maxPrice: '',
    ramSize: [],
    ramType: [],
    cores: [],
    minFreq: '',
    maxFreq: '',
    type: 'pcPost'
  };

  ramSizeFlag = false;
  ramTypeFlag = false;
  coresFlag = false;

  @Output()
  onSubmit: EventEmitter<PcFilter> = new EventEmitter<PcFilter>();

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get(environment.apiUrl+'/pc/minPrice').subscribe((data: string) => { this.initFilter.minPrice = data; });
    this.http.get(environment.apiUrl+'/pc/maxPrice').subscribe((data: string) => { this.initFilter.maxPrice = data; });
    this.http.get(environment.apiUrl+'/pc/cores').subscribe((data: string[]) => { this.initFilter.cores = data; });
    this.http.get(environment.apiUrl+'/pc/minFreq').subscribe((data: string) => { this.initFilter.minFreq = data; });
    this.http.get(environment.apiUrl+'/pc/maxFreq').subscribe((data: string) => { this.initFilter.maxFreq = data; });
    this.http.get(environment.apiUrl+'/pc/ramSize').subscribe((data: string[]) => { this.initFilter.ramSize = data; });
    this.http.get(environment.apiUrl+'/pc/ramModel').subscribe((data: string[]) => { this.initFilter.ramType = data; });
  }

  getFiltredPcProducts() {
    this.checkInputs();
    const filter: PcFilter = {
      minPrice: this.selectedFilter.minPrice,
      maxPrice: this.selectedFilter.maxPrice,
      ramSize: this.selectedFilter.ramSize,
      ramType: this.selectedFilter.ramType,
      cores: this.selectedFilter.cores,
      minFreq: this.selectedFilter.minFreq,
      maxFreq: this.selectedFilter.maxFreq,
      type: 'pcPost'
    };
    this.onSubmit.emit(filter);

    //
    if (this.coresFlag) {
      this.coresFlag = false;
      this.selectedFilter.cores = [];
    }
    if (this.ramTypeFlag) {
      this.ramTypeFlag = false;
      this.selectedFilter.ramType = [];
    }
    if (this.ramSizeFlag) {
      this.ramSizeFlag = false;
      this.selectedFilter.ramSize = [];
    }
  }

  checkInputs() {
    if (this.selectedFilter.minPrice === '') {
      this.selectedFilter.minPrice = this.initFilter.minPrice;
    }

    if (this.selectedFilter.maxPrice === '') {
      this.selectedFilter.maxPrice = this.initFilter.maxPrice;
    }

    if (this.selectedFilter.ramSize.length === 0) {
      this.selectedFilter.ramSize = this.initFilter.ramSize;
      this.ramSizeFlag = true;
    }

    if (this.selectedFilter.ramType.length === 0) {
      this.selectedFilter.ramType = this.initFilter.ramType;
      this.ramTypeFlag = true;
    }

    if (this.selectedFilter.cores.length === 0) {
      this.selectedFilter.cores = this.initFilter.cores;
      this.coresFlag = true;
    }

    if (this.selectedFilter.minFreq === '') {
      this.selectedFilter.minFreq = this.initFilter.minFreq;
    }

    if (this.selectedFilter.maxFreq === '') {
      this.selectedFilter.maxFreq = this.initFilter.maxFreq;
    }
  }

  coresChangeSelect(core) {
    let isInArr = 0;
    for (let i = 0; i < this.selectedFilter.cores.length; i++) {
      if (core === this.selectedFilter.cores[i]) {
        isInArr++;
        this.selectedFilter.cores.splice(i, 1);
      }
    }
    if (isInArr === 0) {
      this.selectedFilter.cores.push(core);
    }
  }

  raxSizeChangeSelect(ram) {
    let isInArr = 0;
    for (let i = 0; i < this.selectedFilter.ramSize.length; i++) {
      if (ram === this.selectedFilter.ramSize[i]) {
        isInArr++;
        this.selectedFilter.ramSize.splice(i, 1);
      }
    }
    if (isInArr === 0) {
      this.selectedFilter.ramSize.push(ram);
    }
  }

  raxTypeChangeSelect(ram) {
    let isInArr = 0;
    for (let i = 0; i < this.selectedFilter.ramType.length; i++) {
      if (ram === this.selectedFilter.ramType[i]) {
        isInArr++;
        this.selectedFilter.ramType.splice(i, 1);
      }
    }
    if (isInArr === 0) {
      this.selectedFilter.ramType.push(ram);
    }
  }
}
