import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {SearchService} from '../services/search.service';
import {BaseComponent} from "../../base.component";

@Component({
  selector: 'app-search-input',
  templateUrl: './search-input.component.html',
  styleUrls: ['./search-input.component.less']
})
export class SearchInputComponent extends BaseComponent implements OnInit {

  searchedTerm: string;
  @Output() resultsFound = new EventEmitter<string[]>();

  constructor(private searchService: SearchService) {
    super();
  }

  ngOnInit() {
  }

  submitSearch(): void {
    if (!this.searchedTerm) {
      return;
    }
    this.safelySubscriptionable(this.searchService.searchTerm(this.searchedTerm))
      .subscribe((results: string[]) => {
        this.resultsFound.emit(results);
      });
  }
}
