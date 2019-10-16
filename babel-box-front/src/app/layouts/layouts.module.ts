import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchTranslationsComponent } from './search-translations/search-translations.component';
import { LanguagesModule } from '../languages/languages.module';
import { SearchModule } from '../search/search.module';
import { ExercisesModule } from '../exercises/exercises.module';

@NgModule({
  declarations: [SearchTranslationsComponent],
  imports: [
    CommonModule,
    LanguagesModule,
    SearchModule,
    ExercisesModule
  ],
  exports: [SearchTranslationsComponent]
})
export class LayoutsModule { }
