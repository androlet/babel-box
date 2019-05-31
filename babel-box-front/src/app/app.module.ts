import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {SearchModule} from './search/search.module';
import {ExercisesModule} from './exercises/exercises.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {LanguagesModule} from "./languages/languages.module";

@NgModule({
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LanguagesModule,
    SearchModule,
    ExercisesModule,
  ],
  declarations: [
    AppComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
