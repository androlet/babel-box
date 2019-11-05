import { Component, OnInit, ViewChild } from '@angular/core';
import { BaseComponent } from '../../base.component';
import { QcmExercise } from '../domain/exercises';
import { ExercisesService } from '../services/exercises.service';

@Component({
  selector: 'app-exercise-translations',
  templateUrl: './exercise-translations.component.html',
  styleUrls: ['./exercise-translations.component.css']
})
export class ExerciseTranslationsComponent extends BaseComponent implements OnInit {

  questionExo: QcmExercise;

  constructor(private exercisesService: ExercisesService) {
    super();
  }

  ngOnInit() {
    this.loadNewQcm();
  }

  isLoading(): boolean {
    return !this.questionExo;
  }

  isLoaded(): boolean {
    return !!this.questionExo;
  }

  loadNewQcm(): void {
    this.safelySubscriptionable(this.exercisesService.getExercise())
    .subscribe(
      exercise => this.questionExo = exercise
    );
  }
}
