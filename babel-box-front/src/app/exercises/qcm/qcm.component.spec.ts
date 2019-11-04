/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ExercisesModuleTesting, QcmComponentTested } from '../exercises.module.testing';
import { QcmComponent } from './qcm.component';

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
  });
});
