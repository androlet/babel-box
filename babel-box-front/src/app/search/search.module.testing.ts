import {Component, NgModule} from '@angular/core';
import {SearchService} from './services/search.service';
import {SearchModule} from './search.module';
import {SearchInputComponent} from './search-input/search-input.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import { SearchTranslationsComponent } from './search-translations/search-translations.component';

@Component({
  selector: 'app-search-input',
  template: ''
})
export class SearchInputComponentTested extends SearchInputComponent {}

@Component({
  selector: 'app-search-translations',
  template: ''
})
export class SearchTranslationsComponentTested extends SearchTranslationsComponent {}

@NgModule({
  imports: [
    SearchModule,
    HttpClientTestingModule
  ],
  declarations: [SearchInputComponentTested, SearchTranslationsComponentTested],
  providers: [SearchService],
  exports: [SearchInputComponentTested, SearchTranslationsComponentTested]
})
export class SearchModuleTesting { }
