import {Dates} from './Dates';

export interface Car {
  id: number;
  plateNumber: string;
  brand: string;
  price: number;
  rent: boolean;
  dates: Dates[];
}
