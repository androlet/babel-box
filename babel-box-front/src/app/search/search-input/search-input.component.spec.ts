import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SearchInputComponent} from './search-input.component';
import {SearchService} from '../services/search.service';
import {of} from 'rxjs';
import {SearchInputComponentTested, SearchModuleTesting} from "../search.module.testing";

describe('SearchInputComponent', () => {
  let component: SearchInputComponentTested;
  let fixture: ComponentFixture<SearchInputComponentTested>;

  let searchService: SearchService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [SearchModuleTesting]
    }).compileComponents();

    fixture = TestBed.createComponent(SearchInputComponentTested);
    component = fixture.componentInstance;

    searchService = TestBed.get(SearchService);
    fixture.detectChanges();
  });

  beforeEach(() => {
    spyOn(component.resultsFound, 'emit').and.callThrough();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should fetch search result and emit its', () => {

    //Given
    const expectedResults = ['termOne', 'termTwo'];
    component.searchedTerm = 'searchedTerm';
    spyOn(searchService, 'searchTerm').and.returnValue(of(expectedResults));

    //When
    component.submitSearch();

    //Then
    expect(component.resultsFound.emit).toHaveBeenCalledWith(expectedResults);
  });
});
