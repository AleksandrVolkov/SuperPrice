import {CommonFilter} from './common-filter';

export class MonitorFilter extends CommonFilter {
  minScreen: string;
  maxScreen: string;
  minScreenFrequency: string;
  maxScreenFrequency: string;
  matrixType: string[];
  screenResolution: string[];
}
