import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {FormsModule} from '@angular/forms';
import {ExercisesService} from './services/exercises.service';
import { ExerciseTranslationsComponent } from './exercise-translations/exercise-translations.component';
import { QcmOptionsComponent } from './qcm-options/qcm-options.component';
import { QcmComponent } from './qcm/qcm.component';
import { LanguagesModule } from '../languages/languages.module';

@NgModule({
  declarations: [ExerciseTranslationsComponent, QcmOptionsComponent, QcmComponent],
  imports: [
    CommonModule,
    MatButtonModule,
    FormsModule,
    LanguagesModule
  ],
  providers: [ExercisesService],
  exports: [ExerciseTranslationsComponent, QcmComponent]
})
export class ExercisesModule {
}

