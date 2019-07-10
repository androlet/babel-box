package com.learning.babelbox.features;

import com.learning.babelbox.features.dto.LanguageOption;
import com.learning.babelbox.services.LanguageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<LanguageOption> getAllLanguages() {
        return LanguageOption.from(languageService.getAllLanguages());
    }
}
