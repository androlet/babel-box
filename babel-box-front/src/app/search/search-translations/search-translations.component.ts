import { Component, OnInit } from '@angular/core';
import { TranslationResults } from 'src/app/search/domain/translation-results';

@Component({
  selector: 'app-search-translations',
  templateUrl: './search-translations.component.html',
  styleUrls: ['./search-translations.component.less']
})
export class SearchTranslationsComponent implements OnInit {

  public translations: TranslationResults;

  constructor() { }

  ngOnInit() {
  }

  loadResults(translations: TranslationResults): void {
    this.translations = translations;
  }

  hasSomeResults(): boolean {
    return !!this.translations;
  }
}
