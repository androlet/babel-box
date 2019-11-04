/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ExercisesModuleTesting, QcmComponentTested } from '../exercises.module.testing';
import { QcmComponent } from './qcm.component';
import { QCM_TRANSLATIONS__ANSWER_EQ_2 } from '../exercise-translations/mock-exo';
import { QcmOption } from '../domain/exercises';

describe('QcmComponent', () => {
  let component: QcmComponent;
  let fixture: ComponentFixture<QcmComponentTested>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ ExercisesModuleTesting ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QcmComponentTested);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
    expect(component.selectedOption).toBe(undefined);
    expect(component.isDescriptionDisplayed()).toBe(false);
  });

  it('should display option description for correction WHEN a qcm option has been selected', () => {
    //given
    const selectedOption = QCM_TRANSLATIONS__ANSWER_EQ_2.options[0];
    component.questionExo = QCM_TRANSLATIONS__ANSWER_EQ_2;

    //when
    component.selectOption(selectedOption);

    //then
    expect(component.selectedOption).toBe(selectedOption);
    expect(component.isDescriptionDisplayed()).toBe(true);
    expect(component.getOptionTranslation()).toBe(selectedOption.translation);
  });
});
