import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import { MatFormFieldModule, MatInputModule, MatButtonModule, MatIconModule } from '@angular/material';
import { SearchTranslationsComponent } from '../search/search-translations/search-translations.component';
import {SearchInputComponent} from './search-input/search-input.component';
import {SearchService} from './services/search.service';
import { LanguagesModule } from '../languages/languages.module';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    LanguagesModule
  ],
  declarations: [SearchInputComponent, SearchTranslationsComponent],
  providers: [SearchService],
  exports: [SearchTranslationsComponent]
})
export class SearchModule { }
