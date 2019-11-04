import { Component, OnInit } from '@angular/core';
import { QCM_TRANSLATIONS__ANSWER_EQ_2 } from '../exercise-translations/mock-exo';

@Component({
  selector: 'app-qcm',
  templateUrl: './qcm.component.html',
  styleUrls: ['./qcm.component.css']
})
export class QcmComponent implements OnInit {

  questionExo = QCM_TRANSLATIONS__ANSWER_EQ_2;

  constructor() { }

  ngOnInit() {
  }

}
