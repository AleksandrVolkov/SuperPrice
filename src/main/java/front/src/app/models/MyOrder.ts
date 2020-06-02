import { Product } from '../backet/product';

export class MyOrder {
  id: number;
  price: number;
  orderDate:Date;
  productCounts:OrderProduct[];

}

export class OrderProduct{
    product:Product;
    count:number;
}