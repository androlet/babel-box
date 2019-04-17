import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SimpleTranslationComponent} from "./simple-translation/simple-translation.component";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [SimpleTranslationComponent],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [SimpleTranslationComponent]
})
export class ExercisesModule {
}

