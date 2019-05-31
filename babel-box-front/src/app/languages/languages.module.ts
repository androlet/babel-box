import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HttpClientModule} from "@angular/common/http";
import {LanguagesService} from "./services/languages.service";
import { LanguagesSelectorComponent } from './languages-selector/languages-selector.component';
import {MatSelectModule} from "@angular/material";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [LanguagesSelectorComponent],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    MatSelectModule
  ],
  providers: [LanguagesService],
  exports: [LanguagesSelectorComponent]
})
export class LanguagesModule { }
