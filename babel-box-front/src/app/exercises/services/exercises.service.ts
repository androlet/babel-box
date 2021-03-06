import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import { QcmExercise } from '../domain/exercises';
import { BABELBOX_API_ROOT } from 'src/app/constants';

@Injectable()
export class ExercisesService {

  constructor(private httpClient: HttpClient) { }

  getExercise(): Observable<QcmExercise> {
    return this.httpClient.get<QcmExercise>(`${BABELBOX_API_ROOT}/translations/exercises/qcm`);
  }
}
