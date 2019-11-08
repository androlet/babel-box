package com.learning.babelbox.features;

import com.learning.babelbox.features.dto.QcmExercise;
import com.learning.babelbox.features.dto.TranslationResults;
import com.learning.babelbox.services.LanguageService;
import com.learning.babelbox.services.SearchService;
import com.learning.babelbox.services.TranslationKnowledgeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/translations")
public class TranslationController {

    private final TranslationKnowledgeService translationKnowledgeService;
    private final SearchService searchService;
    private final LanguageService languageService;

    public TranslationController(TranslationKnowledgeService translationKnowledgeService, SearchService searchService, LanguageService languageService) {
        this.translationKnowledgeService = translationKnowledgeService;
        this.searchService = searchService;
        this.languageService = languageService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public TranslationResults getTranslations(@RequestParam(required = false) String searchedTerm) {
        return new TranslationResults(
                searchService.searchTranslations(
                    languageService.getLanguage("en"),
                    languageService.getLanguage("fr"),
                    searchedTerm
                )
        );
    }

    @RequestMapping(value = "/exercises/qcm", method = RequestMethod.GET)
    public QcmExercise getQcm() {
        return translationKnowledgeService.generateQcmExercise(
            languageService.getLanguage("en"),
            languageService.getLanguage("fr")
        );
    }
}
