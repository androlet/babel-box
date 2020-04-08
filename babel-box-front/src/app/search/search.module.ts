import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
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
