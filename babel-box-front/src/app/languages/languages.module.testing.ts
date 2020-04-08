import {Component, NgModule} from '@angular/core';
import {LanguagesModule} from './languages.module';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {LanguagesSelectorComponent} from './languages-selector/languages-selector.component';
import {TranslationDisplayComponent} from './translation-display/translation-display.component';

@Component({
  selector: 'app-languages-selector',
  template: ''
})
export class LanguagesSelectorComponentTested extends LanguagesSelectorComponent {}

@Component({
  selector: 'app-languages-selector',
  template: ''
})
export class TranslationDisplayComponentTested extends TranslationDisplayComponent {}

@NgModule({
  declarations: [LanguagesSelectorComponentTested, TranslationDisplayComponentTested],
  imports: [
    LanguagesModule,
    HttpClientTestingModule
  ],
  exports: [LanguagesSelectorComponentTested, TranslationDisplayComponentTested]
})
export class LanguagesModuleTesting { }
