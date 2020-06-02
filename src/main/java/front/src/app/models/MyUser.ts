import {MyOrder} from './MyOrder';

export class MyUser {
  id: string;
  userName: string;
  email: string;
  firstName: string;
  lastName: string;
  roles: string;
  orders: MyOrder[];
  picture: string;
}
