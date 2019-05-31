package com.learning.babelbox.features;

import com.learning.babelbox.features.dto.TranslationResults;
import com.learning.babelbox.services.LanguageService;
import com.learning.babelbox.services.TranslationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/translations")
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
}
