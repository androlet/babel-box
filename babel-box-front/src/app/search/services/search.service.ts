import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpParams} from '@angular/common/http';
import {backendUrl} from '../../constants';

@Injectable()
export class SearchService {

  constructor(private httpClient: HttpClient) { }

  searchTerm(term: string): Observable<string[]> {
    const params: HttpParams = new HttpParams({fromObject: {searchedTerm: term}});
    return this.httpClient.get<string[]>(`${backendUrl}/translates/search`, {params});
  }
}
