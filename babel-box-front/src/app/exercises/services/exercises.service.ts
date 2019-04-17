import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class ExercisesService {

  constructor(private httpClient: HttpClient) { }

  getExercise(): Observable<any> {
    return this.httpClient.get('');
  }
}
