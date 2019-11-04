import { Component, OnInit, Input } from '@angular/core';
import { BaseComponent } from 'src/app/base.component';
import { QcmOption, QcmExercise } from '../domain/exercises';
import { TranslationResults } from 'src/app/search/domain/translation-results';

@Component({
  selector: 'app-qcm',
  templateUrl: './qcm.component.html',
  styleUrls: ['./qcm.component.css']
})
export class QcmComponent extends BaseComponent implements OnInit {

  @Input() questionExo: QcmExercise;
  selectedOption: QcmOption;

  constructor() {
    super();
  }

  ngOnInit() {
  }

  selectOption(qcmOption: QcmOption): void {
    this.selectedOption = qcmOption;
  }

  isDescriptionDisplayed(): boolean {
    return !!this.selectedOption;
  }

  getOptionTranslation(): TranslationResults {
    return this.selectedOption.translation;
  }

}
