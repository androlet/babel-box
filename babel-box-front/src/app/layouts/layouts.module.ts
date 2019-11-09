import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LanguagesModule } from '../languages/languages.module';
import { SearchModule } from '../search/search.module';
import { ExercisesModule } from '../exercises/exercises.module';
import { FrontOfficeComponent } from './front-office/front-office.component';
import { AppRoutingModule } from '../app-routing.module';

@NgModule({
  declarations: [FrontOfficeComponent],
  imports: [
    CommonModule,
    AppRoutingModule,
    LanguagesModule,
    SearchModule,
    ExercisesModule
  ],
  exports: [FrontOfficeComponent]
})
export class LayoutsModule { }
