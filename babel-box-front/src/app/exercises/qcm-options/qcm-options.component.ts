import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { BaseComponent } from 'src/app/base.component';
import { QcmExercise, ExerciseOption } from '../domain/exercises';

@Component({
  selector: 'app-qcm-options',
  templateUrl: './qcm-options.component.html',
  styleUrls: ['./qcm-options.component.css']
})
export class QcmOptionsComponent extends BaseComponent implements OnInit {

  @Input() question: QcmExercise;
  @Output() userSelect = new EventEmitter<ExerciseOption>();

  private selectedOption: ExerciseOption;

  constructor() {
    super();
  }

  ngOnInit() {
  }

  private isQcmPristine() {
    return !this.selectedOption;
  }

  selectOption(option: ExerciseOption): void {
    if (this.isQcmPristine()) {
      option.isUserAnswer = true;
    }
    this.selectedOption = option;
    this.userSelect.emit(this.selectedOption);
  }

  isCorrectingRightAnswer(option: ExerciseOption): boolean {
    return !!this.selectedOption && option.isRightAnswer;
  }

  isCorrectingWrongAnswer(option: ExerciseOption): boolean {
    return !!option.isUserAnswer && !option.isRightAnswer;
  }

  isOptionSelected(option: ExerciseOption): boolean {
    return this.selectedOption === option;
  }
}
