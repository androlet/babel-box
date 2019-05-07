import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SearchInputComponent} from './search-input/search-input.component';
import {SearchService} from './services/search.service';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule
  ],
  declarations: [SearchInputComponent],
  providers: [SearchService],
  exports: [SearchInputComponent]
})
export class SearchModule { }
