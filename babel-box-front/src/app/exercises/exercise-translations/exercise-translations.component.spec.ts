/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ExerciseTranslationsComponentTested, ExercisesModuleTesting } from '../exercises.module.testing';
import { HttpTestingController } from '@angular/common/http/testing';
import { BABELBOX_API_ROOT } from 'src/app/constants';
import { QCM_TRANSLATIONS__ANSWER_EQ_2, QCM_TRANSLATIONS__ANSWER_EQ_3 } from './mock-exo';

describe('ExerciseTranslationsComponent', () => {
  let component: ExerciseTranslationsComponentTested;
  let fixture: ComponentFixture<ExerciseTranslationsComponentTested>;
  let mockHttp: HttpTestingController;
  const FETCH_EXERCISES_URL = `${BABELBOX_API_ROOT}/translations/exercises/qcm`;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ExercisesModuleTesting]
    })
    .compileComponents();
    mockHttp = TestBed.get(HttpTestingController);
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExerciseTranslationsComponentTested);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should display spinner WHEN page is loading', () => {
    expect(component.isLoaded()).toBe(false);
    expect(component.isLoading()).toBe(true);
  });

  it('should display first qcm exercise WHEN page is loaded', () => {
    //given
    const questionExo = QCM_TRANSLATIONS__ANSWER_EQ_2;

    //when

    //then
    mockHttp.expectOne(FETCH_EXERCISES_URL).flush(questionExo);
    expect(component.questionExo).toBe(questionExo);
    expect(component.isLoaded()).toBe(true);
    expect(component.isLoading()).toBe(false);
  });

  it('should display next qcm exercise WHEN next button is clicked', () => {
    //given
    component.questionExo = QCM_TRANSLATIONS__ANSWER_EQ_2;
    const nextQuestionExo = QCM_TRANSLATIONS__ANSWER_EQ_3;
    mockHttp.expectOne(FETCH_EXERCISES_URL);

    //when
    component.loadNewQcm();

    //then
    mockHttp.expectOne(FETCH_EXERCISES_URL).flush(nextQuestionExo);
    expect(component.questionExo).toBe(nextQuestionExo);
    expect(component.isLoaded()).toBe(true);
    expect(component.isLoading()).toBe(false);
  });

});
