export interface Result {
  signification: string;
  originalExample?: string;
  translatedExample?: string;
}

export interface TranslationResults {
  from: string;
  to: string;
  originalTerm: string;
  pronunciation: string;
  results: Result[];
}
