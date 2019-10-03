import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {SearchService} from '../services/search.service';
import {BaseComponent} from '../../base.component';
import {TranslationResults} from '../domain/translation-results';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-search-input',
  templateUrl: './search-input.component.html',
  styleUrls: ['./search-input.component.less']
})
export class SearchInputComponent extends BaseComponent implements OnInit {

  searchTranslationsFormControls = new FormGroup({
    searchedTherm: new FormControl('')
  });
  @Output() resultsFound = new EventEmitter<TranslationResults>();

  constructor(private searchService: SearchService) {
    super();
  }

  ngOnInit() {
  }

  private getSearchedTerm(): string {
    return this.searchTranslationsFormControls.get('searchedTherm').value;
  }

  hasSearchedTerm(): boolean {
    return !!this.getSearchedTerm();
  }

  private resetSearchedTerm(): void {
    this.searchTranslationsFormControls.get('searchedTherm').setValue('');
  }

  submitSearch(): void {
    if (!this.getSearchedTerm()) {
      return;
    }
    this.safelySubscriptionable(this.searchService.searchTerm(this.getSearchedTerm()))
      .subscribe((results: TranslationResults) => {
        this.resultsFound.emit(results);
        this.resetSearchedTerm();
      });
  }
}
