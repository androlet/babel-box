package com.learning.babelbox.features.builders;

import com.learning.babelbox.domain.Language;
import com.learning.babelbox.domain.Signification;
import com.learning.babelbox.domain.Translation;
import com.learning.babelbox.domain.Word;

import java.util.List;
import java.util.stream.Collectors;

public class TranslationBuilder {

    public static List<Translation> buildFrom(Language source, Language target, String searchecTerm, List<String> significations) {
        Word originalWord = new Word(source, searchecTerm);
        return significations.stream()
                .map(translation -> new Translation(originalWord, new Signification(target, translation)))
                .collect(Collectors.toList());
    }
}
