import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SearchInputComponent} from './search-input.component';
import {SearchInputComponentTested, SearchModuleTesting} from "../search.module.testing";
import {HttpTestingController} from "@angular/common/http/testing";
import {TranslationResults} from "../domain/translation-results";
import {BABELBOX_API_ROOT} from "../../constants";

describe('SearchInputComponent', () => {
  let component: SearchInputComponentTested;
  let fixture: ComponentFixture<SearchInputComponentTested>;
  let mockHttp: HttpTestingController;

  const expectedResults = {

  } as TranslationResults;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [SearchModuleTesting]
    }).compileComponents();

    fixture = TestBed.createComponent(SearchInputComponentTested);
    component = fixture.componentInstance;

    mockHttp = TestBed.get(HttpTestingController);
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
    const searchedTerm = 'searchedTerm';
    component.searchedTerm = searchedTerm;

    //When
    component.submitSearch();
    mockHttp.expectOne(`${BABELBOX_API_ROOT}/translations/search?searchedTerm=${searchedTerm}`).flush(expectedResults);

    //Then
    expect(component.resultsFound.emit).toHaveBeenCalledWith(expectedResults);
  });
});
