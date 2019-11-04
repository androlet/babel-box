import { QcmExercise } from 'src/app/exercises/domain/exercises';
import { TranslationResults } from 'src/app/search/domain/translation-results';

const mean: TranslationResults = JSON.parse(`{
  "from": "en",
  "to": "fr",
  "originalTerm": "mean",
  "pronunciation": "ˈmiːn",
  "results": [
    {
      "signification": "signifier",
      "originalExample": "What does the word 'available' mean?",
      "translatedExample": "Que signifie le mot \\"available\\" ?"
    }
  ]
}`);

const poor: TranslationResults = JSON.parse(`{
  "from": "en",
  "to": "fr",
  "originalTerm": "poor",
  "pronunciation": "ˈpʊə",
  "results": [
    {
      "signification": "pauvre",
      "originalExample": "Many of us come from poor families.",
      "translatedExample": "Beaucoup d'entre nous viennent de familles pauvres."
    }
  ]
}`);

const test: TranslationResults = JSON.parse(`{
  "from": "en",
  "to": "fr",
  "originalTerm": "test",
  "pronunciation": "ˈtɛst",
  "results": [
    {
      "signification": "interro",
      "originalExample": "I have a German test today; I hope I get good results.",
      "translatedExample": "J'ai une interro d'allemand aujourd'hui. J'espère avoir une bonne note."
    }
  ]
}`);

const screw: TranslationResults = JSON.parse(`{
  "from": "en",
  "to": "fr",
  "originalTerm": "screw",
  "pronunciation": "ˈskruː",
  "results": [
    {
      "signification": "vis",
      "originalExample": "Screws have a better holding power than nails.",
      "translatedExample": "Les vis tiennent mieux que les clous."
    }
  ]
}`);

export const QCM_TRANSLATIONS__ANSWER_EQ_2 = {
  qcmQuestion: poor.originalTerm,
  options: [{
      isRightAnswer: false,
      content: mean.results[0].signification,
      translation: mean
    },
    {
      isRightAnswer: true,
      content: poor.results[0].signification,
      translation: poor
    },
    {
      isRightAnswer: false,
      content: screw.results[0].signification,
      translation: screw
    },
    {
      isRightAnswer: false,
      content: test.results[0].signification,
      translation: test
    }
  ]
} as QcmExercise;
