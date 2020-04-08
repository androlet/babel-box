/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FrontOfficeComponentTested, LayoutsModuleTesting } from '../layouts.module.testing';

describe('FrontOfficeComponent', () => {
  let component: FrontOfficeComponentTested;
  let fixture: ComponentFixture<FrontOfficeComponentTested>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ LayoutsModuleTesting ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FrontOfficeComponentTested);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
