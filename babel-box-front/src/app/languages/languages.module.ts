import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HttpClientModule} from "@angular/common/http";
import {LanguagesService} from "./services/languages.service";
import { LanguagesSelectorComponent } from './languages-selector/languages-selector.component';

@NgModule({
  declarations: [LanguagesSelectorComponent],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  providers: [LanguagesService],
  exports: [LanguagesSelectorComponent]
})
export class LanguagesModule { }
