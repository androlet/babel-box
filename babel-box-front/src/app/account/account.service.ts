import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Credentials} from './credentials';
import {BABELBOX_API_ROOT} from '../constants';

@Injectable()
export class AccountService {

  constructor(private http: HttpClient) { }

  login(credentials: Credentials): Observable<any> {
    return this.http.post<any>(`${BABELBOX_API_ROOT}/login`, credentials);
  }
}
