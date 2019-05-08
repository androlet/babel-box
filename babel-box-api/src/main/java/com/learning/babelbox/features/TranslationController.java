package com.learning.babelbox.features;

import com.learning.babelbox.features.dto.TranslationResults;
import com.learning.babelbox.services.LanguageService;
import com.learning.babelbox.services.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/translations")
@RequiredArgsConstructor
public class TranslationController {

    private final TranslationService translationService;
    private final LanguageService languageService;

    @RequestMapping(value = "/generates", method = RequestMethod.GET)
    public void generateTranslations() {
        languageService.initLanguages("en", "fr");
        translationService.generates();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public TranslationResults getTranslations(@RequestParam(required = false) String searchedTerm) {
        return translationService.getTranslationResults(
                languageService.getLanguage("en"),
                languageService.getLanguage("fr"),
                searchedTerm
        );
    }
}
