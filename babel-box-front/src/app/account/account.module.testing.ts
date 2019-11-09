import {Component, NgModule} from '@angular/core';
import {LoginComponent} from './login/login.component';
import {AccountService} from './account.service';
import {AccountModule} from './account.module';
import {HttpClientTestingModule} from '@angular/common/http/testing';

@Component({
  selector: 'app-login',
  template: ''
})
export class LoginComponentTested extends  LoginComponent {}

@NgModule({
  imports: [
    AccountModule,
    HttpClientTestingModule
  ],
  declarations: [LoginComponentTested],
  providers: [
    AccountService
  ],
  exports: [LoginComponentTested]
})
export class AccountModuleTesting { }
