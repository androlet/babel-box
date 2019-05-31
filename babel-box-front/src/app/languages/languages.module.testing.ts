import {Component, NgModule} from '@angular/core';
import {LanguagesModule} from "./languages.module";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {LanguagesSelectorComponent} from "./languages-selector/languages-selector.component";

@Component({
  selector: 'app-languages-selector',
  template: ''
})
export class LanguagesSelectorComponentTesting extends LanguagesSelectorComponent {}

@NgModule({
  declarations: [LanguagesSelectorComponentTesting],
  imports: [
    LanguagesModule,
    HttpClientTestingModule
  ],
  exports: [LanguagesSelectorComponentTesting]
})
export class LanguagesModuleTesting { }
