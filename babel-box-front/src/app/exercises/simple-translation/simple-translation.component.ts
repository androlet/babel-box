import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ExercisesService} from "../services/exercises.service";

@Component({
  selector: 'app-simple-translation',
  templateUrl: './simple-translation.component.html',
  styleUrls: ['./simple-translation.component.less']
})
export class SimpleTranslationComponent implements OnInit {

  wordToTranslate: string;
  typedAnswer: string;
  private rightAnswer: string;

  constructor(private exercisesService: ExercisesService) { }

  ngOnInit() {
    this.loadExercise();
  }

  private loadExercise(): void {
    this.exercisesService.getExercise().subscribe(
      response => this.rightAnswer = response.answer
    )
  }

  isRightAnswer(): boolean {
    return this.typedAnswer === this.rightAnswer;
  }

  isWrongAnswer(): boolean {
    return !this.isRightAnswer() && !this.isStillAnswering();
  }

  private isStillAnswering(): boolean {
    return !this.typedAnswer || this.rightAnswer.length > this.typedAnswer.length;
  }
}
