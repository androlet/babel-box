import { Component, OnInit, Input } from '@angular/core';
import { TranslationResults } from 'src/app/search/domain/translation-results';

@Component({
  selector: 'app-translation-display',
  templateUrl: './translation-display.component.html',
  styleUrls: ['./translation-display.component.css']
})
export class TranslationDisplayComponent implements OnInit {

  @Input() public readonly translation: TranslationResults;

  constructor() { }

  ngOnInit() {
  }

}
