import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpParams} from '@angular/common/http';
import {BABELBOX_API_ROOT} from '../../constants';
import {TranslationResults} from "../domain/translation-results";

@Injectable()
export class SearchService {

  constructor(private httpClient: HttpClient) { }

  searchTerm(term: string): Observable<TranslationResults> {
    const params: HttpParams = new HttpParams({fromObject: {searchedTerm: term}});
    return this.httpClient.get<TranslationResults>(`${BABELBOX_API_ROOT}/translations/search`, {params});
  }
}
