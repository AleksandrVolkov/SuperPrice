import {CommonFilter} from './common-filter';

export class PrinterFilter extends CommonFilter {
  printerType: string[];
  color: string[];
  format: string[];
  maxSpeed: string;
  minSpeed: string;
  twoSidedPrinting: string[];
}
