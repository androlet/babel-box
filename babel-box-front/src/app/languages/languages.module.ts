import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {LanguagesService} from './services/languages.service';
import { LanguagesSelectorComponent } from './languages-selector/languages-selector.component';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import {MatListModule} from '@angular/material/list';
import {FormsModule} from '@angular/forms';
import {TranslationDisplayComponent} from './translation-display/translation-display.component';

@NgModule({
  declarations: [LanguagesSelectorComponent, TranslationDisplayComponent],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    MatSelectModule,
    MatListModule,
    MatIconModule
  ],
  providers: [LanguagesService],
  exports: [LanguagesSelectorComponent, TranslationDisplayComponent]
})
export class LanguagesModule { }
