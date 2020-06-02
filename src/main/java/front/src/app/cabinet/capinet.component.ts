import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';

//import { KeycloakService } from '../auth/keycloack/keycloack.service';
import {HttpClient} from '@angular/common/http';

import {FormControl, Validators, NgForm, FormGroupDirective} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material';
import {MyUser} from '../models/MyUser';
import {environment} from 'src/environments/environment';
import {UploadFilesComponent} from '../file/upload.component';


export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'cabinet',
  templateUrl: './capinet.component.html',
})
export class CabinetComponent implements OnInit {


  emailFormControl = new FormControl({value: '', disabled: true}, [
    Validators.required,
    Validators.email,
  ]);

  matcher = new MyErrorStateMatcher();
  profile: MyUser;
  @Input() flag: boolean = true;
  // contracts: Contract[];
  step = 0;
  public image: string;
  panelOpenState:any;
  // @Input() fileInfos;
  // @Output() myfileInfos:EventEmitter<any>;

  constructor(//private keycloakService: KeycloakService,
    private http: HttpClient
  ) {
  }


  public ngOnInit(): void {
    //  if(!this.Role()){
    //      this.router.navigate(['home']);
    //  }
    this.http.get(environment.apiUrl + '/user/cabinet').subscribe((data: MyUser) => this.profile = data);
  }

  // public isManager(): boolean {
  //     return this.keycloakService.hasAnyRole(['manager']);
  // }

  // public isAdmin(): boolean {
  //     return this.keycloakService.hasAnyRole(['admin']);
  // }

  // public getContracts() {
  //     this.contractService.getContracts().subscribe(
  //         data => {
  //             this.contracts = data;
  //         }
  //     );
  // }

  // public logout() {
  //     this.keycloakService.logout();
  // }

  // Role():boolean{
  //    let a= this.keycloakService.role();
  //    return a;
  // }
  isEdit() {
    this.flag = false;
  }

  edit() {

    this.profile.orders = null;
    this.http.put(environment.apiUrl + '/user/edit', this.profile).subscribe((data: MyUser) => {
        this.profile = data;
        this.flag = true;
      }
    );

  }

  change(ev: any) {
    this.profile.picture = ev;
  }

}
