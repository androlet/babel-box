package com.learning.babelbox.features;

import com.learning.babelbox.domain.Language;
import com.learning.babelbox.features.dto.QcmExercise;
import com.learning.babelbox.features.dto.QuestionExercise;
import com.learning.babelbox.features.dto.TranslationResults;
import com.learning.babelbox.services.LanguageService;
import com.learning.babelbox.services.TranslationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/api/translations")
public class TranslationController {

    private final TranslationService translationService;
    private final LanguageService languageService;

    public TranslationController(TranslationService translationService, LanguageService languageService) {
        this.translationService = translationService;
        this.languageService = languageService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public TranslationResults getTranslations(@RequestParam(required = false) String searchedTerm) {
        return new TranslationResults(
                translationService.getTranslationResults(
                    languageService.getLanguage("en"),
                    languageService.getLanguage("fr"),
                    searchedTerm
                )
        );
    }

    @RequestMapping(value = "/exercises/qcm", method = RequestMethod.GET)
    public QcmExercise getQcm() {
        Language from = languageService.getLanguage("en");
        Language to = languageService.getLanguage("fr");
        QcmExercise qcmExercise = new QcmExercise(
                asList("mean", "poor", "screw", "test").stream()
                        .map(word -> new QcmExercise.QcmOption(translationService.getTranslationResults(from, to, word).get(0)))
                        .collect(Collectors.toList())
        );
        qcmExercise.setRightAnswer(2);
        return qcmExercise;
    }
}
