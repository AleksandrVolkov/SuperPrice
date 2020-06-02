import {Component, OnInit} from '@angular/core';
import {Chart} from 'chart.js';


@Component({
  selector: 'app-timetable',
  templateUrl: './timetable.html',
  styleUrls: ['./timetable.css']
})
export class TimetableComponent implements OnInit {
  LineChart = [];
  BarChart = [];
  PieChart = [];

  ngOnInit() {
    // Line chart:
    this.LineChart = new Chart('График продаж', {
      fontColor: 'white',
      type: 'line',
      data: {
        labels: ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентабрь', 'Октябрь', 'Ноябрь', 'Декабрь'],
        datasets: [{
          label: 'Кол-во продуктов продано за месяц',
          data: [9, 7, 3, 5, 2, 10, 15, 16, 19, 3, 1, 9],
          fill: true,
          lineTension: 0.8,
          borderColor: 'green',
          borderWidth: 2,
          hoverBackgroundColor: 'rgba(232,105,90,0.8)',
          hoverBorderColor: 'orange',
        },
          {
            label: 'NumbMonths',
            data: [9, 7, 3, 5, 2, 10, 15, 16, 19, 3, 1, 9],
            // borderColor: chartColors.yellow,
            pointBackgroundColor: 'black',
            fill: false,
            lineTension: 1.2,
            borderColor: 'red',
            borderWidth: 1
          }]

      },
      options: {
        fontColor: 'white',
        title: {
          fontColor: 'white',
          text: 'Line Chart',
          display: true
        },
        scales: {
          yAxes: [{
            ticks: {
              fontColor: 'white',
              beginAtZero: true
            }
          }],
          xAxes: [{
            ticks: {
              fontColor: 'white',
              beginAtZero: true
            },
          }]
        }
      }
    });


  }
}
