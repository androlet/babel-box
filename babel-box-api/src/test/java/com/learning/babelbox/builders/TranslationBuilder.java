package com.learning.babelbox.builders;

import com.learning.babelbox.domain.Language;
import com.learning.babelbox.domain.Translation;
import com.learning.babelbox.domain.Word;

import java.util.List;
import java.util.stream.Collectors;

public class TranslationBuilder {

    private static final Language initial = new Language("en");
    private static final Language targeted = new Language("fr");

    public static List<Translation> buildFrom(String searchecTerm, List<String> translations) {
        Word originalWord = new Word(initial, searchecTerm);
        return translations.stream()
                .map(translation -> new Translation(originalWord, new Word(targeted, translation)))
                .collect(Collectors.toList());
    }
}
