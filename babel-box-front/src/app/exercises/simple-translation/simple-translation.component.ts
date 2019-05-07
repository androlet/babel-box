import {Component, OnInit} from '@angular/core';
import {ExercisesService} from "../services/exercises.service";
import {BaseComponent} from "../../base.component";

@Component({
  selector: 'app-simple-translation',
  templateUrl: './simple-translation.component.html',
  styleUrls: ['./simple-translation.component.less']
})
export class SimpleTranslationComponent extends BaseComponent implements OnInit {

  wordToTranslate: string;
  typedAnswer: string;
  private rightAnswer: string;

  constructor(private exercisesService: ExercisesService) {
    super();
  }

  ngOnInit() {
    this.loadExercise();
  }

  private loadExercise(): void {
    this.safelySubscriptionable(this.exercisesService.getExercise())
      .subscribe(
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
