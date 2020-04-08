import { Component, NgModule, Injectable } from '@angular/core';
import {LoginComponent} from './login/login.component';
import {AccountService} from './account.service';
import {AccountModule} from './account.module';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import { Router } from '@angular/router';

@Injectable()
export class RouterMock {
  private currentUrl = 'initial';

  getCurrentUrl(): string {
    return this.currentUrl;
  }

  navigateByUrl(url: string) {
    this.currentUrl = url;
  }
}

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
    AccountService,
    {
      provide: Router, useClass: RouterMock
    }
  ],
  exports: [LoginComponentTested]
})
export class AccountModuleTesting { }
