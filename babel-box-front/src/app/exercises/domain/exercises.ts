import { TranslationResults } from '../../search/domain/translation-results';

export interface ExerciseOption {
  isRightAnswer: boolean;
  isUserAnswer: boolean;
  content: TranslationResults;
}

export interface QcmExercise {
  options: ExerciseOption[];
}
