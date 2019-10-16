import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Component, NgModule } from '@angular/core';
import { ExercisesModule } from './exercises.module';
import { QcmOptionsComponent } from './qcm-options/qcm-options.component';
import { ExercisesService } from './services/exercises.service';

@Component({
  selector: 'app-qcm-options',
  template: ''
})
export class QcmOptionsComponentTested extends QcmOptionsComponent {}

@NgModule({
  declarations: [QcmOptionsComponentTested],
  imports: [
    ExercisesModule,
    HttpClientTestingModule
  ],
  providers: [ExercisesService],
  exports: [QcmOptionsComponentTested]
})
export class ExercisesModuleTesting {
}

