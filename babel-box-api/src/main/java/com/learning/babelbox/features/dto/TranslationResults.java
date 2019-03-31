package com.learning.babelbox.features.dto;

import com.learning.babelbox.domain.Translation;
import com.learning.babelbox.domain.Word;

import java.util.List;
import java.util.stream.Collectors;

public class TranslationResults {

    private Word originalTerm;
    private List<String> translationTerms;

    public TranslationResults(List<Translation> translations) {
        translationTerms = translations.stream()
                .map(translation -> translation.getTranslatedTerm().getSpelling())
                .collect(Collectors.toList());
        originalTerm = translations.get(0).getOriginalTerm();
    }

    public Word getOriginalTerm() {
        return originalTerm;
    }

    public List<String> getTranslationTerms() {
        return translationTerms;
    }
}
