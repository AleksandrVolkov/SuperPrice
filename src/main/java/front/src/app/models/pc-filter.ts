import {CommonFilter} from './common-filter';

export class PcFilter extends CommonFilter {

  // тип оперативки
  ramType: string;

  // размер оперативки
  ramSize: number;

  // частота процессора
  cpuFrequency: number;

  // кол-во ядер процессора
  cpuCores: number;
}
