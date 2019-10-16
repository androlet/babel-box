import { Component, OnInit } from '@angular/core';
import { BaseComponent } from '../../base.component';
import { QCM_TRANSLATIONS__ANSWER_EQ_2 } from './mock-exo';

@Component({
  selector: 'app-exercise-translations',
  templateUrl: './exercise-translations.component.html',
  styleUrls: ['./exercise-translations.component.css']
})
export class ExerciseTranslationsComponent extends BaseComponent implements OnInit {

  questionExo = QCM_TRANSLATIONS__ANSWER_EQ_2;

  constructor() {
    super();
  }

  ngOnInit() {
  }

}
