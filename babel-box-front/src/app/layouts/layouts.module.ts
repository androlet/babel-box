import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { LanguagesModule } from '../languages/languages.module';
import { SearchModule } from '../search/search.module';
import { ExercisesModule } from '../exercises/exercises.module';
import { FrontOfficeComponent } from './front-office/front-office.component';
import { AppRoutingModule } from '../app-routing.module';
import { NavBarComponent } from './nav-bar/nav-bar.component';

@NgModule({
  declarations: [FrontOfficeComponent, NavBarComponent],
  imports: [
    CommonModule,
    MatButtonModule,
    MatToolbarModule,
    AppRoutingModule,
    LanguagesModule,
    SearchModule,
    ExercisesModule
  ],
  exports: [FrontOfficeComponent]
})
export class LayoutsModule { }
