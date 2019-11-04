import { TranslationResults } from '../../search/domain/translation-results';

export interface QcmOption {
  isRightAnswer: boolean;
  isUserAnswer: boolean;
  content: string;
  translation: TranslationResults;
}

export interface QcmExercise {
  qcmQuestion: string;
  options: QcmOption[];
}
