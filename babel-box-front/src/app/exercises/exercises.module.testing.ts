import {Component, NgModule} from '@angular/core';
import {SimpleTranslationComponent} from "./simple-translation/simple-translation.component";
import {ExercisesService} from "./services/exercises.service";
import {ExercisesModule} from "./exercises.module";
import {HttpClientTestingModule} from "@angular/common/http/testing";

@Component({
  selector: 'app-simple-translation',
  template: ''
})
export class SimpleTranslationComponentTested extends SimpleTranslationComponent {}

@NgModule({
  declarations: [SimpleTranslationComponentTested],
  imports: [
    ExercisesModule,
    HttpClientTestingModule
  ],
  providers: [ExercisesService],
  exports: [SimpleTranslationComponentTested]
})
export class ExercisesModuleTesting {
}

