package com.learning.babelbox.features.dto;

import com.learning.babelbox.domain.Translation;
import com.learning.babelbox.domain.Word;

import java.util.List;
import java.util.stream.Collectors;

public class TranslationResults {

    private String from;
    private String to;
    private String originalTerm;
    private String pronunciation;
    private List<Result> results;

    public TranslationResults(List<Translation> translations) {
        Word originalWord = translations.get(0).getOriginalTerm();
        from = originalWord.getLanguage().getLocaleCode();
        to = translations.get(0).getSignification().getLanguage().getLocaleCode();
        originalTerm = originalWord.getSpelling();
        pronunciation = originalWord.getPronunciation();
        results = translations.stream().map(
                    translation -> new Result(
                        translation.getSignification().getDescription(),
                        translation.getOriginalExample(),
                        translation.getTranslatedExample()
                    )
                  ).collect(Collectors.toList());
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getOriginalTerm() {
        return originalTerm;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public List<Result> getResults() {
        return results;
    }

    public static class Result {
        String signification;
        String originalExample;
        String translatedExample;

        public Result(String signification, String originalExample, String translatedExample) {
            this.signification = signification;
            this.originalExample = originalExample;
            this.translatedExample = translatedExample;
        }

        public String getSignification() {
            return signification;
        }

        public String getOriginalExample() {
            return originalExample;
        }

        public String getTranslatedExample() {
            return translatedExample;
        }
    }
}
