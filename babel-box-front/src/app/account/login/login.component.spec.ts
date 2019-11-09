import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AccountModuleTesting, LoginComponentTested} from '../account.module.testing';
import {HttpTestingController} from '@angular/common/http/testing';
import {FormGroup} from '@angular/forms';
import {BABELBOX_API_ROOT} from '../../constants';

fdescribe('LoginComponent', () => {
  let component: LoginComponentTested;
  let fixture: ComponentFixture<LoginComponentTested>;
  let mockHttp: HttpTestingController;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ AccountModuleTesting ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginComponentTested);
    component = fixture.componentInstance;

    mockHttp = TestBed.get(HttpTestingController);
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
    expect(component.isCredentialsInvalid()).toBe(false);
  });

  it('should display error message WHEN credentials are invalid AND form has been submitted', () => {
    //given
    component.credentialsFormControls = {
      valid: false,
      invalid: true,
      get: (name: string) => {
        const values = {
          username: {value: 'test@test'},
          password: {value: 'password'}
        };
        return values[name];
      }
    } as FormGroup;

    //when
    component.tryLogin();
    mockHttp.expectOne({url: `${BABELBOX_API_ROOT}/login`, method: 'POST'})
      .flush(null, {headers: {}, status: 404, statusText: 'NOT_FOUND'});

    //then
    mockHttp.expectNone('/api/login');
    expect(component.isCredentialsInvalid()).toBe(true);
  });

  it('should trigger event "logged in" WHEN credentials are valid AND form has been submitted', () => {
    //given
    component.credentialsFormControls = {
      valid: true,
      invalid: false,
      get: (name: string) => {
        const values = {
          username: {value: 'test@test'},
          password: {value: 'password'}
        };
        return values[name];
      }
    } as FormGroup;

    //when
    component.tryLogin();
    mockHttp.expectOne({url: `${BABELBOX_API_ROOT}/login`, method: 'POST'}).flush(null, {headers: {}, status: 200, statusText: 'OK'});

    //then
    expect(component.isCredentialsInvalid()).toBe(false);
  });
});
