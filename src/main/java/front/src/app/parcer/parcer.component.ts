import {Component, EventEmitter, OnChanges, OnDestroy, OnInit, Output, SimpleChanges} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ParseFilter} from './parse-filter';
import {PcFilter} from '../filters/pc-filter';
import {User} from '../cabinet/user';
import {Subject} from 'rxjs';
import {ActivatedRoute, Params} from '@angular/router';
import {takeUntil} from 'rxjs/operators';
import {ParseTable} from './parse-table';
import {ReturnString} from './return-string';
import {Page} from './page';
import {AuthenticationService} from '../services/authentication-service';
import {environment} from 'src/environments/environment';

@Component({
  selector: 'app-parcer',
  templateUrl: './parcer.component.html',
  styleUrls: ['./parcer.component.css']
})
export class ParcerComponent implements OnInit, OnDestroy {
  private destroy$ = new Subject<undefined>();
  cron: '';
  cronStatus = 'Не известно';
  cronDate = 'Не известна';
  cronStart = false;
  cronStop = true;


  config: any;
  products: ParseTable[];
  parsStatus = 'Не активен.';

  parserStat = false;
  disabledStart = false;
  disabledStop = true;
  private email: any;
  initFilter: ParseFilter = {
    citilinkParse: '',
    dnsParse: '',
    pcParse: '',
    monitorParse: '',
    printerParse: ''
  };
  selectedFilter: ParseFilter = {
    citilinkParse: '',
    dnsParse: '',
    pcParse: '',
    monitorParse: '',
    printerParse: ''
  };
  @Output()
  onSubmit: EventEmitter<ParseFilter> = new EventEmitter<ParseFilter>();
  url = environment.apiUrl;

  // url = 'http://domennoeimya99.xyz';

  constructor(private http: HttpClient, private route: ActivatedRoute, public auth: AuthenticationService) {
    // this.route.params.pipe(
    //   takeUntil(this.destroy$)
    // ).subscribe((params: Params) => {
    //   this.parsStatus = params.parsStatus;
    //   this.disabled = params.disabled;
    // });
  }


  change() {
    this.http.get(this.url + '/parseTable/pageable_table/' + (this.config.currentPage - 1), {
      headers: {'Authorization': 'CShop_' + localStorage.getItem('token')}
    })
      .subscribe((data: Page) => {
        this.products = data.content;
      });
  }

  startCron() {
    this.checkInputs();
    if ((this.initFilter.citilinkParse !== '' || this.initFilter.dnsParse !== '') &&
      (this.initFilter.pcParse !== '' || this.initFilter.monitorParse !== '' || this.initFilter.printerParse !== '')) {

      this.http.get(this.url + '/user/cabinet', {
        headers: {'Authorization': 'CShop_' + localStorage.getItem('token')}
      }).subscribe((data: User) => {
        this.email = data.email;
        this.http.get(this.url + '/scheduled/scheduled_start?citilink=' + this.initFilter.citilinkParse
          + '&dns=' + this.initFilter.dnsParse
          + '&pc=' + this.initFilter.pcParse
          + '&monitor=' + this.initFilter.monitorParse
          + '&printer=' + this.initFilter.printerParse
          + '&userName=' + this.email
          + '&cron=' + this.cron, {
          headers: {'Authorization': 'CShop_' + localStorage.getItem('token')}
        }).subscribe((dataStart: ReturnString) => {
          this.cronStatus = dataStart.info.split(';')[0];
          this.cronDate = dataStart.info.split(';')[1];
          this.cronStart = dataStart.disabledStart;
          this.cronStop = dataStart.disabledStop;
          this.disabledStart = true;
          this.parsStatus = 'Смотрите расписание';
        });
      });
    } else {
      this.parsStatus = 'Укажите параметры парсинга';
    }
  }

  stopCron() {
    this.http.get(this.url + '/scheduled/scheduled_stop', {
      headers: {'Authorization': 'CShop_' + localStorage.getItem('token')}
    }).subscribe((dataStart: ReturnString) => {
      this.cronStatus = dataStart.info.split(';')[0];
      this.cronDate = dataStart.info.split(';')[1];
      this.cronStart = dataStart.disabledStart;
      this.cronStop = dataStart.disabledStop;
      this.disabledStart = false;
      this.parsStatus = 'Не известно';

      this.http.get(this.url + '/parseTable/pageable_table/' + (this.config.currentPage - 1), {
        headers: {'Authorization': 'CShop_' + localStorage.getItem('token')}
      })
        .subscribe((dataPage: Page) => {
          this.products = dataPage.content;
        });
    });
  }


  ngOnInit() {
    this.http.get(this.url + '/parcer/get_status', {
      headers: {'Authorization': 'CShop_' + localStorage.getItem('token')}
    }).subscribe((data: ReturnString) => {
      this.disabledStart = data.disabledStart;
      this.disabledStop = data.disabledStop;
      this.parsStatus = data.info;

    });
    // this.http.get(this.url + '/user/cabinet').subscribe((data: User) => {
    //   this.email = data.email;
    // });

    // this.http.get(this.url + '/parseTable/table').subscribe((data: ParseTable[]) => {
    //   this.flags = data;
    this.http.get(this.url + '/scheduled/get_next_date', {
      headers: {'Authorization': 'CShop_' + localStorage.getItem('token')}
    }).subscribe((dataCronStatus: ReturnString) => {
      this.cronDate = dataCronStatus.info;
      this.cronStart = dataCronStatus.disabledStart;
      this.cronStop = dataCronStatus.disabledStop;
    });
    this.http.get(this.url + '/parseTable/pageable_table/0', {
      headers: {'Authorization': 'CShop_' + localStorage.getItem('token')}
    }).subscribe((data: Page) => {
      this.products = data.content;
      this.config = {
        itemsPerPage: 10,
        currentPage: 1,
        totalItems: data.totalPages * 10
      };
      this.stsp();
    });
    // const start = !(this.disabledStart || this.cronStart);
  }

  stsp() {
    // const start = !(this.disabledStart || this.cronStart);
    // const stop = !(this.disabledStop || this.cronStop);
    if (this.disabledStart) {
      this.disabledStart = true;
      this.disabledStop = false;
      this.cronStop = true;
      this.cronStart = true;
    } else {
      if (this.cronStart) {
        this.disabledStart = true;
        this.disabledStop = true;
        this.cronStart = true;
        this.cronStop = false;
      }
    }
  }

  getSelectedParseFields() {
    this.disabledStart = true;
    this.cronStart = true;
    this.parserStat = true;
    this.checkInputs();
    this.selectedFilter.citilinkParse = this.selectedFilter.citilinkParse.toString();
    this.selectedFilter.dnsParse = this.selectedFilter.dnsParse.toString();
    this.selectedFilter.pcParse = this.selectedFilter.pcParse.toString();
    this.selectedFilter.monitorParse = this.selectedFilter.monitorParse.toString();
    this.selectedFilter.printerParse = this.selectedFilter.printerParse.toString();


    this.http.get(this.url + '/user/cabinet', {
      headers: {'Authorization': 'CShop_' + localStorage.getItem('token')}
    }).subscribe((data: User) => {
      this.email = data.email;
      this.http.get(this.url + '/parcer/parse_start?citilink=' + this.initFilter.citilinkParse
        + '&dns=' + this.initFilter.dnsParse
        + '&pc=' + this.initFilter.pcParse
        + '&monitor=' + this.initFilter.monitorParse
        + '&printer=' + this.initFilter.printerParse
        + '&userName=' + this.email, {
        headers: {'Authorization': 'CShop_' + localStorage.getItem('token')}
      }).subscribe((dataStart: ReturnString) => {
        this.parsStatus = dataStart.info;
        this.disabledStart = dataStart.disabledStart;
        this.disabledStop = dataStart.disabledStop;
        if (this.parserStat) {
          this.http.get(this.url + '/parcer/parse_join', {
            headers: {'Authorization': 'CShop_' + localStorage.getItem('token')}
          }).subscribe((dataJoin: ReturnString) => {
            this.parsStatus = dataJoin.info;
            this.disabledStart = dataJoin.disabledStart;
            this.disabledStop = dataJoin.disabledStop;
            this.cronStart = false;

            this.http.get(this.url + '/parseTable/pageable_table/' + (this.config.currentPage - 1), {
              headers: {'Authorization': 'CShop_' + localStorage.getItem('token')}
            })
              .subscribe((dataPage: Page) => {
                this.products = dataPage.content;
                this.parserStat = false;
              });

          });
        }
      });
    });

  }

  pageChanged(event) {
    this.config.currentPage = event;
  }

  checkInputs() {
    if (this.selectedFilter.citilinkParse === '') {
      this.selectedFilter.citilinkParse = this.initFilter.citilinkParse;
    }

    if (this.selectedFilter.dnsParse === '') {
      this.selectedFilter.dnsParse = this.initFilter.dnsParse;
    }

    if (this.selectedFilter.pcParse === '') {
      this.selectedFilter.pcParse = this.initFilter.pcParse;
    }

    if (this.selectedFilter.monitorParse === '') {
      this.selectedFilter.monitorParse = this.initFilter.monitorParse;
    }

    if (this.selectedFilter.printerParse === '') {
      this.selectedFilter.printerParse = this.initFilter.printerParse;
    }
  }

  changeSelected(param) {
    if (param == 'citilink') {
      if (this.initFilter.citilinkParse === '') {
        this.initFilter.citilinkParse = param;
      } else {
        this.initFilter.citilinkParse = '';
      }
    }
    if (param == 'dns') {
      if (this.initFilter.dnsParse === '') {
        this.initFilter.dnsParse = param;
      } else {
        this.initFilter.dnsParse = '';
      }
    }
    if (param == 'pc') {
      if (this.initFilter.pcParse === '') {
        this.initFilter.pcParse = param;
      } else {
        this.initFilter.pcParse = '';
      }
    }
    if (param == 'monitor') {
      if (this.initFilter.monitorParse === '') {
        this.initFilter.monitorParse = param;
      } else {
        this.initFilter.monitorParse = '';
      }
    }
    if (param == 'printer') {
      if (this.initFilter.printerParse === '') {
        this.initFilter.printerParse = param;
      } else {
        this.initFilter.printerParse = '';
      }
    }
  }


  parseStop() {
    this.disabledStop = true;
    this.parserStat = false;
    this.http.get(this.url + '/parcer/stop', {
      headers: {'Authorization': 'CShop_' + localStorage.getItem('token')}
    }).subscribe((data: ReturnString) => {
      this.parsStatus = data.info;
      this.disabledStart = data.disabledStart;
      this.cronStart = false;
      // this.disabledStart = false;
      // this.disabledStop = data.disabledStop;
      // this.disabledStop = true;
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

}
