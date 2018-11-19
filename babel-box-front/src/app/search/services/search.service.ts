import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';

@Injectable()
export class SearchService {

  constructor() { }

  searchTerm(term: string): Observable<string[]> {
    return of([]);
  }
}
