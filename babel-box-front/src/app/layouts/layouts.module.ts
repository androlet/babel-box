import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchTranslationsComponent } from './search-translations/search-translations.component';
import { LanguagesModule } from '../languages/languages.module';
import { SearchModule } from '../search/search.module';

@NgModule({
  declarations: [SearchTranslationsComponent],
  imports: [
    CommonModule,
    LanguagesModule,
    SearchModule
  ],
  exports: [SearchTranslationsComponent]
})
export class LayoutsModule { }
