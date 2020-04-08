/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { NavBarComponentTested, LayoutsModuleTesting } from '../layouts.module.testing';

describe('NavBarComponent', () => {
  let component: NavBarComponentTested;
  let fixture: ComponentFixture<NavBarComponentTested>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ LayoutsModuleTesting ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavBarComponentTested);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
