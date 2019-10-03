import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchTranslationsComponent } from './search-translations.component';

describe('SearchTranslationsComponent', () => {
  let component: SearchTranslationsComponent;
  let fixture: ComponentFixture<SearchTranslationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchTranslationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchTranslationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
