import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SimpleTranslationComponent } from './simple-translation.component';
import {By} from "@angular/platform-browser";
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {ExercisesService} from "../services/exercises.service";

describe('SimpleTranslationComponent', () => {
  let component: SimpleTranslationComponent;
  let fixture: ComponentFixture<SimpleTranslationComponent>;
  let mockHttp: HttpTestingController;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SimpleTranslationComponent ],
      providers: [ExercisesService],
      imports: [HttpClientTestingModule]
    })
    .compileComponents();

    mockHttp = TestBed.get(HttpTestingController);
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SimpleTranslationComponent);
    component = fixture.componentInstance;
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
      fixture.detectChanges();

      //then
      expect(fixture.debugElement.query(By.css('.success-indicator')).nativeElement.innerText).toContain('Success');
      expect(fixture.debugElement.query(By.css('.fail-indicator'))).toBeNull();
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
      expect(fixture.debugElement.query(By.css('.success-indicator'))).toBeNull();
      expect(fixture.debugElement.query(By.css('.fail-indicator')).nativeElement.innerText).toContain('Fail');
    });
  });

});
