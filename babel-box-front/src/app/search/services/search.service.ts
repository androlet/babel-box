import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpParams} from '@angular/common/http';
import {BABELBOX_API_ROOT} from '../../constants';

@Injectable()
export class SearchService {

  constructor(private httpClient: HttpClient) { }

  searchTerm(term: string): Observable<string[]> {
    const params: HttpParams = new HttpParams({fromObject: {searchedTerm: term}});
    return this.httpClient.get<string[]>(`${BABELBOX_API_ROOT}/translations/search`, {params});
  }
}
