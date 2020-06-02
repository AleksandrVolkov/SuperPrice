import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthenticationService} from '../../services/authentication-service';
import {MyUser} from '../../models/MyUser';
import {environment} from 'src/environments/environment';

@Component({
  selector: 'app-users-manager',
  templateUrl: './users-manager.component.html',
  styleUrls: ['./users-manager.component.css']
})
export class UsersManagerComponent implements OnInit{
  users: MyUser[];
  allUsers: MyUser[];
  admins: MyUser[];

  constructor(private http: HttpClient, private auth: AuthenticationService) {
  }


  move() {
    this.http.get(environment.apiUrl + '/api/v1/admin/get_admins', {
      headers: {Authorization: 'CShop_' + localStorage.getItem('token')}
    })
      .subscribe((data: MyUser[]) => {
        this.admins = data;
      });

    this.http.get(environment.apiUrl + '/api/v1/admin/get_all_users', {
      headers: {Authorization: 'CShop_' + localStorage.getItem('token')}
    })
      .subscribe((data: MyUser[]) => {
        this.allUsers = data;
      });

    this.http.get(environment.apiUrl + '/api/v1/admin/get_users', {
      headers: {Authorization: 'CShop_' + localStorage.getItem('token')}
    })
      .subscribe((data: MyUser[]) => {
        this.users = data;
      });
  }

  ngOnInit() {
    this.move();

  }

  deleteAdmin(user: MyUser) {
    this.http.get(environment.apiUrl + '/api/v1/admin/remove_admin/' + user.id, {
      headers: {Authorization: 'CShop_' + localStorage.getItem('token')}
    })
      .subscribe((data: MyUser) => {
        this.admins = this.admins.filter(item => item.id !== data.id);
      });
    this.move();
  }

  addAdmin(user: MyUser) {
    this.http.get(environment.apiUrl + '/api/v1/admin/set_admin/' + user.id, {
      headers: {Authorization: 'CShop_' + localStorage.getItem('token')}
    })
      .subscribe((data: MyUser) => {
        this.admins.push(data);
      });
    this.move();
  }

  // onlyUsers(users: MyUser[]) {
  //   console.log(users);
  //   for (let i = 0; i < users.length; i++) {
  //     const roles = users[i].roles.split(' ');
  //     console.log(roles);
  //     for (let j = 0; j < roles.length; j++) {
  //       if (roles[j] === 'ROLE_ADMIN') {
  //         this.users.splice(i, 1);
  //       }
  //     }
  //   }
  //   console.log(users);
  //   return users;
  // }

}
