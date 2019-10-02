import {Component, NgModule} from '@angular/core';
import {LanguagesModule} from './languages.module';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {LanguagesSelectorComponent} from './languages-selector/languages-selector.component';
import {TranslationDisplayComponent} from './translation-display/translation-display.component';

@Component({
  selector: 'app-languages-selector',
  template: ''
})
export class LanguagesSelectorComponentTesting extends LanguagesSelectorComponent {}

@Component({
  selector: 'app-languages-selector',
  template: ''
})
export class TranslationDisplayComponentTesting extends TranslationDisplayComponent {}

@NgModule({
  declarations: [LanguagesSelectorComponentTesting, TranslationDisplayComponentTesting],
  imports: [
    LanguagesModule,
    HttpClientTestingModule
  ],
  exports: [LanguagesSelectorComponentTesting, TranslationDisplayComponentTesting]
})
export class LanguagesModuleTesting { }
