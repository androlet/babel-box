import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';

import { QCM_TRANSLATIONS__ANSWER_EQ_2, QCM_TRANSLATIONS__ANSWER_EQ_3 } from '../exercise-translations/mock-exo';
import { QcmExercise } from '../domain/exercises';

@Injectable()
export class ExercisesService {

  constructor(private httpClient: HttpClient) { }

  lastExercise: QcmExercise;

  getExercise(): Observable<QcmExercise> {
    if (this.lastExercise === QCM_TRANSLATIONS__ANSWER_EQ_2) {
      this.lastExercise = QCM_TRANSLATIONS__ANSWER_EQ_3;
    } else {
      this.lastExercise = QCM_TRANSLATIONS__ANSWER_EQ_2;
    }
    return of(JSON.parse(JSON.stringify(this.lastExercise)));
  }
}
