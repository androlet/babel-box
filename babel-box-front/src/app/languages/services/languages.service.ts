import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {LanguageOption} from '../domain/language-option';
import {BABELBOX_API_ROOT} from '../../constants';

@Injectable()
export class LanguagesService {

  constructor(private httpClient: HttpClient) { }

  getLanguageOptions(): Observable<LanguageOption[]> {
    return this.httpClient.get<LanguageOption[]>(`${BABELBOX_API_ROOT}/languages`);
  }
}
