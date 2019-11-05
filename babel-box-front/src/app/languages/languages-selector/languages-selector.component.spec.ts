import {ComponentFixture, TestBed} from '@angular/core/testing';

import {LanguagesModuleTesting, LanguagesSelectorComponentTesting} from '../languages.module.testing';
import {HttpTestingController} from '@angular/common/http/testing';
import {BABELBOX_API_ROOT} from '../../constants';

const languageOptions = [
  {
    id: 1,
    code: 'en'
  },
  {
    id: 2,
    code: 'fr'
  },
  {
    id: 3,
    code: 'es'
  }
];

describe('LanguagesSelectorComponent', () => {
  let component: LanguagesSelectorComponentTesting;
  let fixture: ComponentFixture<LanguagesSelectorComponentTesting>;
  let mockHttp: HttpTestingController;

  const expectOption = (option, code, isOriginal, isTarget) => {
    expect(option.code).toBe(code);
    expect(component.isSelectedAsOriginal(option)).toBe(isOriginal);
    expect(component.isSelectedAsTarget(option)).toBe(isTarget);
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [LanguagesModuleTesting]
    }).compileComponents();

    fixture = TestBed.createComponent(LanguagesSelectorComponentTesting);
    component = fixture.componentInstance;

    mockHttp = TestBed.get(HttpTestingController);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('SHOULD display all languages available ' +
    'AND select first values in the list by default' +
    'WHEN the component is loaded', () => {
    //given
    //when
    fixture.detectChanges();
    mockHttp.expectOne(BABELBOX_API_ROOT + '/languages').flush(languageOptions);

    //then
    const originalList = component.getOriginalLanguageOptions();
    expectOption(originalList[0], 'en', true, false);
    expectOption(originalList[1], 'es', false, false);
    const targetList = component.getTargetLanguageOptions();
    expectOption(targetList[0], 'fr', false, true);
    expectOption(targetList[1], 'es', false, false);
  });

  it('SHOULD select ' +
    'AND select first values in the list by default' +
    'WHEN the component is loaded', () => {

    //given
    fixture.detectChanges();
    mockHttp.expectOne(BABELBOX_API_ROOT + '/languages').flush(languageOptions);

    //when
    component.original = languageOptions[2];
    component.target = languageOptions[0];

    //then
    const originalList = component.getOriginalLanguageOptions();
    expectOption(originalList[0], 'fr', false, false);
    expectOption(originalList[1], 'es', true, false);
    const targetList = component.getTargetLanguageOptions();
    expectOption(targetList[0], 'en', false, true);
    expectOption(targetList[1], 'fr', false, false);
  });

  it('SHOULD reverse languages selection' +
    'WHEN the reverse button is clicked', () => {

    //given
    fixture.detectChanges();
    mockHttp.expectOne(BABELBOX_API_ROOT + '/languages').flush(languageOptions);
    component.original = languageOptions[2];
    component.target = languageOptions[0];

    //when
    component.reverseLanguageSelected();

    //then
    const originalList = component.getOriginalLanguageOptions();
    expectOption(originalList[0], 'en', true, false);
    expectOption(originalList[1], 'fr', false, false);
    const targetList = component.getTargetLanguageOptions();
    expectOption(targetList[0], 'fr', false, false);
    expectOption(targetList[1], 'es', false, true);
  });

});
