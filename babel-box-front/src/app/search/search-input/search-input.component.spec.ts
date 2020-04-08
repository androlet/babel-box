import { HttpTestingController } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BABELBOX_API_ROOT } from '../../constants';
import { TranslationResults } from '../domain/translation-results';
import { SearchInputComponentTested, SearchModuleTesting } from '../search.module.testing';
import { FormGroup } from '@angular/forms';


describe('SearchInputComponent', () => {
  let component: SearchInputComponentTested;
  let fixture: ComponentFixture<SearchInputComponentTested>;
  let mockHttp: HttpTestingController;

  const expectedResults = {

  } as TranslationResults;

  const buildFormGroup = fields => {
    return {
      valid: true,
      invalid: false,
      get: (name: string) => {
        fields[name].setValue = (val: any) => fields[name].value = val;
        return fields[name];
      }
    } as FormGroup;
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [SearchModuleTesting]
    }).compileComponents();

    fixture = TestBed.createComponent(SearchInputComponentTested);
    component = fixture.componentInstance;

    mockHttp = TestBed.inject(HttpTestingController);
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
    const searchedTermValue = 'word';
    component.searchTranslationsFormControls = buildFormGroup({
        searchedTherm: {value: searchedTermValue}
    });

    //When
    component.submitSearch();

    //Then
    mockHttp.expectOne(`${BABELBOX_API_ROOT}/translations/search?searchedTerm=${searchedTermValue}`).flush(expectedResults);
    expect(component.resultsFound.emit).toHaveBeenCalledWith(expectedResults);
    expect(component.searchTranslationsFormControls.get('searchedTherm').value).toBe('');
  });
});
