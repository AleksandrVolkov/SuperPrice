import {Component, OnChanges, Output, EventEmitter, LOCALE_ID} from '@angular/core';
import {Chart} from 'chart.js';
import { HttpClient } from '@angular/common/http';
import { Order } from '../orders/order';
import { environment } from 'src/environments/environment';
import { MatDatepickerInputEvent } from '@angular/material';
import { DatePipe, getLocaleId } from '@angular/common';
import {AuthenticationService} from "../../services/authentication-service";



@Component({
  selector: 'app-graph',
  templateUrl: './graphic.html',
  styleUrls: ['./graphic.css']
})
export class GraphicComponent {
  LineChart = [];
  LineChart1 = [];
  BarChart = [];
  PieChart = [];
  orders:any[];
  date:string[]=[];
  count:number[]=[];
  counter: any;
  minDate: Date;
  maxDate: Date;
  myDate:Date;
  myDate1:Date;
  flag:boolean;
  constructor(private http: HttpClient,private datePipe: DatePipe, public auth: AuthenticationService) {

    this.http.get(environment.apiUrl+'/order/mindate').subscribe((data:Date)=>this.minDate=data)
    this.http.get(environment.apiUrl+'/order/maxdate').subscribe((data:Date)=>this.maxDate=data)
    this.myDate=this.minDate;
    this.myDate1=this.maxDate;
    this.flag=true;
  }


  @Output()
  dateChange:EventEmitter< MatDatepickerInputEvent<any>>;

  someMethodName(date:any) {
    if(this.flag){
    this.date=[];
    this.count=[];
    this.myDate == null ? this.myDate=this.maxDate : this.myDate;
    this.myDate1 == null ? this.myDate1=this.maxDate : this.myDate1;
    this.http.get(environment.apiUrl+'/order/orders?mindate='+this.datePipe.transform(this.myDate,"yyyy-MM-dd")
                                                  +'&maxdate='+this.datePipe.transform(this.myDate1,"yyyy-MM-dd"))
                                                  .subscribe((data: any[]) => {
      for(let i=0;i<data.length;i++){
        this.date[i]=data[i][0];
        this.count[i]=data[i][1];
      }
    //  var progress = document.getElementById('animationProgress');
      this.LineChart = new Chart('lineChart', {
        fontColor: 'black',
        type: 'line',
        data: {
          labels: this.date,
          datasets: [{
            label: 'Кол-во заказов ',
            data:this.count,
            fill: true,
            lineTension: 0.5,
            borderColor: 'green',
            borderWidth: 2,
            hoverBackgroundColor: 'rgb(0,0,0)',
            hoverBorderColor: 'rgb(0, 0, 0)',
          },
            // {
            //   label: 'NumbMonths',
            //   data:this.count,
            //   // borderColor: chartColors.yellow,
            //   pointBackgroundColor: 'black',
            //   fill: false,
            //   lineTension: 1.2,
            //   borderColor: 'red',
            //   borderWidth: 1
            // }
          ]

        },
        options: {
          fontColor: 'black',
          title: {
            fontColor: 'black',
            text: 'График продаж',
            display: true
          },
          animation: {
            duration: 2000,
            onProgress: function(animation) {

              //progress.value = animation.currentStep / animation.numSteps;
            },
            onComplete: function() {
              window.setTimeout(function() {
               // progress.value = 0;
              }, 3000);
            }
          },
          scales: {
            yAxes: [{
              gridLines:{
                drawBorder:true,
                color:'black'
              },
              ticks: {
                fontColor: 'black',
                beginAtZero: true
              }
            }],
            xAxes: [{
              gridLines:{
                drawBorder:true,
                color:'black'
              },
              ticks: {
                fontColor: 'black',
                beginAtZero: true
              },
            }]
          }
        }

      });

    });
  }else{
    this.date=[];
    this.count=[];
    this.myDate == null ? this.myDate=this.maxDate : this.myDate;
    this.myDate1 == null ? this.myDate1=this.maxDate : this.myDate1;
    this.http.get(environment.apiUrl+'/order/bysum?mindate='+this.datePipe.transform(this.myDate,"yyyy-MM-dd")
                                                  +'&maxdate='+this.datePipe.transform(this.myDate1,"yyyy-MM-dd"))
                                                  .subscribe((data: any[]) => {
      for(let i=0;i<data.length;i++){
        this.date[i]=data[i][0];
        this.count[i]=data[i][1];
      }
    //  var progress = document.getElementById('animationProgress');
      this.LineChart = new Chart('lineChart', {
        fontColor: 'black',
        type: 'line',
        data: {
          labels: this.date,
          datasets: [{
            label: 'Сумма продаж ',
            data:this.count,
            fill: true,
            lineTension: 0.5,
            borderColor: 'green',
            borderWidth: 2,
            hoverBackgroundColor: 'rgb(0,0,0)',
            hoverBorderColor: 'rgb(0, 0, 0)',
          },
            // {
            //   label: 'NumbMonths',
            //   data:this.count,
            //   // borderColor: chartColors.yellow,
            //   pointBackgroundColor: 'black',
            //   fill: false,
            //   lineTension: 1.2,
            //   borderColor: 'red',
            //   borderWidth: 1
            // }
          ]

        },
        options: {
          fontColor: 'black',
          title: {
            fontColor: 'black',
            text: 'Сумма заказов ',
            display: true
          },
          animation: {
            duration: 2000,
            onProgress: function(animation) {

              //progress.value = animation.currentStep / animation.numSteps;
            },
            onComplete: function() {
              window.setTimeout(function() {
               // progress.value = 0;
              }, 3000);
            }
          },
          scales: {
            yAxes: [{
              gridLines:{
                drawBorder:true,
                color:'black'
              },
              scaleLabel: {
                display: true,
                labelString: "Сумма в рублях",
                fontFamily: "Arial",
                fontColor: "black",
                fontSize:"16"
            },
              ticks: {
                fontColor: 'black',
                beginAtZero: true
              }
            }],
            xAxes: [{
              gridLines:{
                drawBorder:true,
                color:'black'
              },
              ticks: {
                fontColor: 'black',
                beginAtZero: true
              },
            }]
          }
        }

      });

    });
  }
}

  ngOnInit() {
   this.date=[];
    this.count=[];
    this.myDate == null ? this.myDate=this.maxDate : this.myDate;
    this.myDate1 == null ? this.myDate1=this.maxDate : this.myDate1;
    this.http.get(environment.apiUrl+'/order/orders?mindate='+this.datePipe.transform(this.myDate,"yyyy-MM-dd")
                                                  +'&maxdate='+this.datePipe.transform(this.myDate1,"yyyy-MM-dd"))
                                                  .subscribe((data: any[]) => {
      for(let i=0;i<data.length;i++){
        this.date[i]=data[i][0];
        this.count[i]=data[i][1];
      }

     
      //var progress = document.getElementById('animationProgress');
      this.LineChart = new Chart('lineChart', {
        fontColor: 'black',
        type: 'line',
        data: {
          labels: this.date,
          datasets: [{
            label: 'Кол-во заказов ',
            data:this.count,
            fill: true,
            lineTension: 0.5,
            borderColor: 'green',
            borderWidth: 2,
            hoverBackgroundColor: 'rgb(0,0,0)',
            hoverBorderColor: 'rgb(0, 0, 0)',
          },
            // {
            //   label: 'NumbMonths',
            //   data:this.count,
            //   // borderColor: chartColors.yellow,
            //   pointBackgroundColor: 'black',
            //   fill: false,
            //   lineTension: 1.2,
            //   borderColor: 'red',
            //   borderWidth: 1
            // }
          ]

        },
        options: {
          fontColor: 'black',
          title: {
            fontColor: 'black',
            text: 'График продаж',
            display: true
          },
          animation: {
            duration: 2000,
            onProgress: function(animation) {

              //progress.value = animation.currentStep / animation.numSteps;
            },
            onComplete: function() {
              window.setTimeout(function() {
               // progress.value = 0;
              }, 3000);
            }
          },
          scales: {
            yAxes: [{
              gridLines:{
                drawBorder:true,
                color:'black'
              },
        
              ticks: {
                fontColor: 'black',
                beginAtZero: true
              }
            }],
            xAxes: [{
              gridLines:{
                drawBorder:true,
                color:'black'
              },
              ticks: {
                fontColor: 'black',
                beginAtZero: true
              },
            }]
          }
        }

      });

    });
  }

  SecondGraph(){
    this.flag=false;
    this.date=[];
    this.count=[];
    this.myDate1 == null ? this.myDate1=this.maxDate : this.myDate1;
    this.http.get(environment.apiUrl+'/order/bysum?mindate='+this.datePipe.transform(this.myDate,"yyyy-MM-dd")
                                                  +'&maxdate='+this.datePipe.transform(this.myDate1,"yyyy-MM-dd"))
                                                  .subscribe((data: any[]) => {
      for(let i=0;i<data.length;i++){
        this.date[i]=data[i][0];
        this.count[i]=data[i][1];
      }
      console.log(this.count);
     //var progress = document.getElementById('animationProgress');
      this.LineChart = new Chart('lineChart2', {
        fontColor: 'black',
        type: 'line',
        data: {
          labels: this.date,
          datasets: [{
            label: 'Сумма Заказов ',
            data:this.count,
            fill: true,
            lineTension: 0.5,
            borderColor: 'green',
            borderWidth: 2,
            hoverBackgroundColor: 'rgb(0,0,0)',
            hoverBorderColor: 'rgb(0, 0, 0)',
          },
            // {
            //   label: 'NumbMonths',
            //   data:this.count,
            //   // borderColor: chartColors.yellow,
            //   pointBackgroundColor: 'black',
            //   fill: false,
            //   lineTension: 1.2,
            //   borderColor: 'red',
            //   borderWidth: 1
            // }
          ]

        },
        options: {
        
          tooltips: {
              callbacks: {
                  label: function(tooltipItem, data) {
                      return tooltipItem.yLabel.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');
                  }
              }
          },
        
          fontColor: 'black',
          title: {
            fontColor: 'black',
            text: 'Сумма продаж',
            display: true
          },
          animation: {
            duration: 2000,
            onProgress: function(animation) {
              //progress.value = animation.currentStep / animation.numSteps;
            },
            onComplete: function() {
              window.setTimeout(function() {
               // progress.value = 0;
              }, 3000);
            }
          },
          scales: {
            yAxes: [{
              gridLines:{
                drawBorder:true,
                color:'black'
              },
              scaleLabel: {
                display: true,
                labelString: "Сумма в рублях",
                fontFamily: "Arial",
                fontColor: "black",
                fontSize:"16"
            },
              ticks: {
                fontColor: 'black',
                beginAtZero: true
              }
            }],
            xAxes: [{
              gridLines:{
                drawBorder:true,
                color:'black'
              },
              ticks: {
                fontColor: 'black',
                beginAtZero: true
              },
            }]
          }
        }
      });

    });


  }

  FirstGraph(){
    this.flag=true;
    this.myDate1 == null ? this.myDate1=this.maxDate : this.myDate1;
    this.http.get(environment.apiUrl+'/order/orders?mindate='+this.datePipe.transform(this.myDate,"yyyy-MM-dd")
                                                  +'&maxdate='+this.datePipe.transform(this.myDate1,"yyyy-MM-dd"))
                                                  .subscribe((data: any[]) => {
      for(let i=0;i<data.length;i++){
        this.date[i]=data[i][0];
        this.count[i]=data[i][1];
      }
     // var progress = document.getElementById('animationProgress');
      this.LineChart = new Chart('lineChart', {
        fontColor: 'black',
        type: 'line',
        data: {
          labels: this.date,
          datasets: [{
            label: 'Кол-во заказов ',
            data:this.count,
            fill: true,
            lineTension: 0.5,
            borderColor: 'green',
            borderWidth: 2,
            hoverBackgroundColor: 'rgb(0,0,0)',
            hoverBorderColor: 'rgb(0, 0, 0)',
          },
            // {
            //   label: 'NumbMonths',
            //   data:this.count,
            //   // borderColor: chartColors.yellow,
            //   pointBackgroundColor: 'black',
            //   fill: false,
            //   lineTension: 1.2,
            //   borderColor: 'red',
            //   borderWidth: 1
            // }
          ]

        },
        options: {
          fontColor: 'black',
          title: {
            fontColor: 'black',
            text: 'График продаж',
            display: true
          },
          animation: {
            duration: 2000,
            onProgress: function(animation) {

              //progress.value = animation.currentStep / animation.numSteps;
            },
            onComplete: function() {
              window.setTimeout(function() {
               // progress.value = 0;
              }, 3000);
            }
          },
          scales: {
            yAxes: [{
              gridLines:{
                drawBorder:true,
                color:'black'
              },
              ticks: {
                fontColor: 'black',
                beginAtZero: true
              }
            }],
            xAxes: [{
              gridLines:{
                drawBorder:true,
                color:'black'
              },
              ticks: {
                fontColor: 'black',
                beginAtZero: true
              },
            }]
          }
        }

      });

    });
  }

}
