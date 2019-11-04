import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ExercisesModuleTesting, QcmOptionsComponentTested } from '../exercises.module.testing';
import { QCM_TRANSLATIONS__ANSWER_EQ_2 } from '../exercise-translations/mock-exo';
import { QcmOption } from '../domain/exercises';

describe('QcmOptionsComponent', () => {
  let component: QcmOptionsComponentTested;
  let fixture: ComponentFixture<QcmOptionsComponentTested>;

  const expectOptionStates = (option: QcmOption, correctingRightAnswer: boolean, wrongAnswer: boolean, isSelected: boolean) => {
    expect(component.isCorrectingRightAnswer(option)).toBe(correctingRightAnswer);
    expect(component.isCorrectingWrongAnswer(option)).toBe(wrongAnswer);
    expect(component.isOptionSelected(option)).toBe(isSelected);
  };

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ ExercisesModuleTesting ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QcmOptionsComponentTested);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    //given
    component.question = QCM_TRANSLATIONS__ANSWER_EQ_2;

    //when

    //then
    expect(component).toBeTruthy();
    expectOptionStates(component.question.options[0], false, false, false);
    expectOptionStates(component.question.options[1], false, false, false);
    expectOptionStates(component.question.options[2], false, false, false);
    expectOptionStates(component.question.options[3], false, false, false);
  });

  it('SHOULD display the correction WHEN user select right option', () => {
    //given
    component.question = QCM_TRANSLATIONS__ANSWER_EQ_2;
    spyOn(component.userSelect, 'emit');

    //when
    const selectedOption = component.question.options[1];
    component.selectOption(selectedOption);

    //then
    expect(component.userSelect.emit).toHaveBeenCalledWith(selectedOption);
    expectOptionStates(component.question.options[0], false, false, false);
    expectOptionStates(component.question.options[1], true, false, true);
    expectOptionStates(component.question.options[2], false, false, false);
    expectOptionStates(component.question.options[3], false, false, false);
  });

  it('SHOULD display the correction WHEN user select wrong option', () => {
    //given
    component.question = QCM_TRANSLATIONS__ANSWER_EQ_2;
    spyOn(component.userSelect, 'emit');

    //when
    const selectedOption = component.question.options[2];
    component.selectOption(selectedOption);

    //then
    expect(component.userSelect.emit).toHaveBeenCalledWith(selectedOption);
    expectOptionStates(component.question.options[0], false, false, false);
    expectOptionStates(component.question.options[1], true, false, false);
    expectOptionStates(component.question.options[2], false, true, true);
    expectOptionStates(component.question.options[3], false, false, false);
  });

  it('SHOULD keep the correction displayed WHEN user select another option', () => {
    //given
    component.question = QCM_TRANSLATIONS__ANSWER_EQ_2;
    spyOn(component.userSelect, 'emit');

    //when
    const initialOptionChosen = component.question.options[2];
    const currentOptionSelected = component.question.options[3];
    component.selectOption(initialOptionChosen);
    component.selectOption(currentOptionSelected);

    //then
    expect(component.userSelect.emit).toHaveBeenCalledWith(initialOptionChosen);
    expect(component.userSelect.emit).toHaveBeenCalledWith(currentOptionSelected);
    expectOptionStates(component.question.options[0], false, false, false);
    expectOptionStates(component.question.options[1], true, false, false);
    expectOptionStates(component.question.options[2], false, true, false);
    expectOptionStates(component.question.options[3], false, false, true);
  });
});
