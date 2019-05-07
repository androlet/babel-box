import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SimpleTranslationComponent} from './simple-translation.component';
import {HttpTestingController} from "@angular/common/http/testing";
import {ExercisesModuleTesting, SimpleTranslationComponentTested} from "../exercises.module.testing";

describe('SimpleTranslationComponent', () => {
  let component: SimpleTranslationComponentTested;
  let fixture: ComponentFixture<SimpleTranslationComponentTested>;
  let mockHttp: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        ExercisesModuleTesting
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(SimpleTranslationComponentTested);
    component = fixture.componentInstance;

    mockHttp = TestBed.get(HttpTestingController);
  });


  it('should create', () => {
    fixture.detectChanges();
    expect(component).toBeTruthy();
  });

  describe('SimpleTranslationComponent', () => {

    it('SHOULD advertise learner ' +
      'WHEN the typed response is correct', () => {
      //given
      fixture.detectChanges();
      mockHttp.expectOne('').flush({answer: 'test'});

      //when
      component.typedAnswer = 'test';

      //then
      expect(component.isRightAnswer()).toBe(true);
      expect(component.isWrongAnswer()).toBe(false);
    });

    it('SHOULD advertise learner ' +
      'WHEN the typed response is wrong', () => {
      //given
      fixture.detectChanges();
      mockHttp.expectOne('').flush({answer: 'test'});

      //when
      component.typedAnswer = 'fake';
      fixture.detectChanges();

      //then
      expect(component.isRightAnswer()).toBe(false);
      expect(component.isWrongAnswer()).toBe(true);
    });
  });

});
