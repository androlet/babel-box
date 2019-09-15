import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AccountService} from "../account.service";
import {BaseComponent} from "../../base.component";
import {Credentials} from "../credentials";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less']
})
export class LoginComponent extends BaseComponent implements OnInit {

  credentialsFormControls = new FormGroup({
    username: new FormControl('test@yopmail.com', [Validators.required, Validators.email]),
    password: new FormControl('password', Validators.required)
  });

  private isCredentialsValid = false;
  private hasFormBeenSubmit = false;

  constructor(private accountService: AccountService) {
    super();
  }

  ngOnInit() {
  }

  private retrieveForminputs(): Credentials {
    return {
      username: this.credentialsFormControls.get('username').value,
      password: this.credentialsFormControls.get('password').value
    };
  }

  isCredentialsInvalid() {
    return this.hasFormBeenSubmit && !this.isCredentialsValid;
  }

  tryLogin() {
    this.hasFormBeenSubmit = true;
    this.safelySubscriptionable(this.accountService.login(this.retrieveForminputs())).subscribe(() => {
      this.isCredentialsValid = true;
    }, (error) => {
      this.isCredentialsValid = false;
    });
  }

}
