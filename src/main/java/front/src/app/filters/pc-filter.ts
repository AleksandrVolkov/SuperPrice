import {CommonFilter} from './common-filter';

export class PcFilter extends CommonFilter {

  ramSize: string[];
  ramType: string[];
  cores: string[];
  minFreq: string;
  maxFreq: string;
}
