package com.learning.babelbox.features.dto;

import com.learning.babelbox.domain.Translation;
import com.learning.babelbox.domain.Word;

import java.util.List;
import java.util.stream.Collectors;

public class TranslationResults {

    private Word originalTerm;
    private List<String> significations;

    public TranslationResults(List<Translation> translations) {
        significations = translations.stream()
                .map(translation -> translation.getSignification().getDescription())
                .collect(Collectors.toList());
        originalTerm = translations.get(0).getOriginalTerm();
    }

    public Word getOriginalTerm() {
        return originalTerm;
    }

    public List<String> getSignifications() {
        return significations;
    }
}
