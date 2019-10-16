import {Component, NgModule} from '@angular/core';
import {SearchService} from './services/search.service';
import {SearchModule} from './search.module';
import {SearchInputComponent} from './search-input/search-input.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';

@Component({
  selector: 'app-search-input',
  template: ''
})
export class SearchInputComponentTested extends SearchInputComponent {}

@NgModule({
  imports: [
    SearchModule,
    HttpClientTestingModule
  ],
  declarations: [SearchInputComponentTested],
  providers: [SearchService],
  exports: [SearchInputComponentTested]
})
export class SearchModuleTesting { }
