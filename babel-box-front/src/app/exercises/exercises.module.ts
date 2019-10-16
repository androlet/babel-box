import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {FormsModule} from '@angular/forms';
import {ExercisesService} from './services/exercises.service';
import { ExerciseTranslationsComponent } from './exercise-translations/exercise-translations.component';
import { QcmOptionsComponent } from './qcm-options/qcm-options.component';

@NgModule({
  declarations: [ExerciseTranslationsComponent, QcmOptionsComponent],
  imports: [
    CommonModule,
    MatButtonModule,
    FormsModule
  ],
  providers: [ExercisesService],
  exports: [ExerciseTranslationsComponent]
})
export class ExercisesModule {
}

