import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SimpleTranslationComponent} from "./simple-translation/simple-translation.component";
import {FormsModule} from "@angular/forms";
import {ExercisesService} from "./services/exercises.service";

@NgModule({
  declarations: [SimpleTranslationComponent],
  imports: [
    CommonModule,
    FormsModule
  ],
  providers: [ExercisesService],
  exports: [SimpleTranslationComponent]
})
export class ExercisesModule {
}

