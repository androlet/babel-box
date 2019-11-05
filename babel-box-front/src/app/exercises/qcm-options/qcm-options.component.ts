import { Component, OnInit, Input, Output, EventEmitter, OnChanges } from '@angular/core';
import { BaseComponent } from 'src/app/base.component';
import { QcmExercise, QcmOption } from '../domain/exercises';

@Component({
  selector: 'app-qcm-options',
  templateUrl: './qcm-options.component.html',
  styleUrls: ['./qcm-options.component.css']
})
export class QcmOptionsComponent extends BaseComponent implements OnInit, OnChanges {

  @Input() question: QcmExercise;
  @Output() userSelect = new EventEmitter<QcmOption>();

  private selectedOption: QcmOption;

  constructor() {
    super();
  }

  ngOnInit() {
  }

  ngOnChanges() {
    this.selectedOption = null;
  }

  private isQcmPristine() {
    return !this.selectedOption;
  }

  selectOption(option: QcmOption): void {
    if (this.isQcmPristine()) {
      option.isUserAnswer = true;
    }
    this.selectedOption = option;
    this.userSelect.emit(this.selectedOption);
  }

  isCorrectingRightAnswer(option: QcmOption): boolean {
    return !!this.selectedOption && option.isRightAnswer;
  }

  isCorrectingWrongAnswer(option: QcmOption): boolean {
    return !!option.isUserAnswer && !option.isRightAnswer;
  }

  isOptionSelected(option: QcmOption): boolean {
    return this.selectedOption === option;
  }
}
