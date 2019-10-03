import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SearchInputComponent} from './search-input/search-input.component';
import {SearchService} from './services/search.service';
import {ReactiveFormsModule} from '@angular/forms';
import { MatFormFieldModule, MatInputModule, MatButtonModule, MatIconModule } from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule
  ],
  declarations: [SearchInputComponent],
  providers: [SearchService],
  exports: [SearchInputComponent]
})
export class SearchModule { }
