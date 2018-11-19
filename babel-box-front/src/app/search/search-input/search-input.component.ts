import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {SearchService} from '../services/search.service';

@Component({
  selector: 'app-search-input',
  templateUrl: './search-input.component.html',
  styleUrls: ['./search-input.component.less']
})
export class SearchInputComponent implements OnInit {

  searchedTerm: string;
  @Output() resultsFound = new EventEmitter<string[]>();

  constructor(private searchService: SearchService) { }

  ngOnInit() {
  }

  submitSearch(): void {
    this.searchService.searchTerm(this.searchedTerm)
      .subscribe((results: string[]) => {
        this.resultsFound.emit(results);
      });
  }
}
