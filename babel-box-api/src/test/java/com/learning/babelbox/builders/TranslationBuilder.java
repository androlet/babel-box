package com.learning.babelbox.builders;

import com.learning.babelbox.domain.Language;
import com.learning.babelbox.domain.Translation;
import com.learning.babelbox.domain.Word;

import java.util.List;
import java.util.stream.Collectors;

public class TranslationBuilder {

    public static List<Translation> buildFrom(Language source, Language target, String searchecTerm, List<String> translations) {
        Word originalWord = new Word(source, searchecTerm);
        return translations.stream()
                .map(translation -> new Translation(originalWord, new Word(target, translation)))
                .collect(Collectors.toList());
    }
}
