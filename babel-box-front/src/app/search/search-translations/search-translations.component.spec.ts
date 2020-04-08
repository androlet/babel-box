import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { SearchModuleTesting, SearchTranslationsComponentTested } from '../search.module.testing';

describe('SearchTranslationsComponent', () => {
  let component: SearchTranslationsComponentTested;
  let fixture: ComponentFixture<SearchTranslationsComponentTested>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ SearchModuleTesting ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchTranslationsComponentTested);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
