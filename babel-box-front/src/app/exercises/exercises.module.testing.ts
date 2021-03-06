import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Component, NgModule } from '@angular/core';
import { ExercisesModule } from './exercises.module';
import { QcmOptionsComponent } from './qcm-options/qcm-options.component';
import { ExercisesService } from './services/exercises.service';
import { QcmComponent } from './qcm/qcm.component';
import { ExerciseTranslationsComponent } from './exercise-translations/exercise-translations.component';

@Component({
  selector: 'app-qcm-options',
  template: ''
})
export class QcmOptionsComponentTested extends QcmOptionsComponent {}

@Component({
  selector: 'app-qcm',
  template: ''
})
export class QcmComponentTested extends QcmComponent {}

@Component({
  selector: 'app-exercise-translations',
  template: ''
})
export class ExerciseTranslationsComponentTested extends ExerciseTranslationsComponent {}

@NgModule({
  declarations: [QcmOptionsComponentTested, QcmComponentTested, ExerciseTranslationsComponentTested],
  imports: [
    ExercisesModule,
    HttpClientTestingModule
  ],
  providers: [ExercisesService],
  exports: [QcmOptionsComponentTested, QcmComponentTested, ExerciseTranslationsComponentTested]
})
export class ExercisesModuleTesting {
}

