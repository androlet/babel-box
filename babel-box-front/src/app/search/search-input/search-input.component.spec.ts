import {async, ComponentFixture, inject, TestBed} from '@angular/core/testing';

import {SearchInputComponent} from './search-input.component';
import {Component, ViewChild} from '@angular/core';
import {SearchService} from '../services/search.service';
import {of} from 'rxjs';
import {SearchModule} from '../search.module';

describe('SearchInputComponent', () => {
  let host: HostComponent;
  let component: SearchInputComponent;
  let fixture: ComponentFixture<HostComponent>;

  @Component({
    template: `<app-search-input (resultsFound)="refreshResults($event)"></app-search-input>`
  })
  class HostComponent {
    results: string[];
    @ViewChild(SearchInputComponent) child;
    refreshResults(results: string[]) {
      this.results = results;
    }
  }

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [SearchModule],
      declarations: [ HostComponent ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HostComponent);
    host = fixture.componentInstance;
    component = host.child;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should fetch search result and emit its', async(inject(
    [SearchService],
    (searchService) => {
      //Given
      const expectedResults = ['termOne', 'termTwo'];
      component.searchedTerm = 'searchedTerm';
      spyOn(searchService, 'searchTerm').and.returnValue(of(expectedResults));

      //When
      component.submitSearch();

      //Then
      expect(host.results).toEqual(expectedResults);
    })));
});
