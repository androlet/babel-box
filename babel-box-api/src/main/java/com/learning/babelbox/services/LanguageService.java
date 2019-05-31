package com.learning.babelbox.services;

import com.learning.babelbox.domain.Language;
import com.learning.babelbox.repository.LanguageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Transactional(readOnly = true)
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Language getLanguage(String localeCode) {
        return languageRepository.findByLocaleCode(localeCode);
    }

    @Transactional
    public Language createOrGet(String localCode) {
        Language language = getLanguage(localCode);
        if(null == language) {
            language = create(localCode);
        }
        return language;
    }

    @Transactional
    public Language create(String localeCode) {
        return languageRepository.save(new Language(localeCode));
    }
}
