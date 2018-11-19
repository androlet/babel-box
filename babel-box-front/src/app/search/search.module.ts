import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchInputComponent } from './search-input/search-input.component';
import {SearchService} from './services/search.service';
import {FormsModule} from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    FormsModule
  ],
  declarations: [SearchInputComponent],
  providers: [SearchService],
  exports: [SearchInputComponent]
})
export class SearchModule { }
