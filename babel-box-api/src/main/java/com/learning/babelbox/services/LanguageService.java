package com.learning.babelbox.services;

import com.learning.babelbox.domain.Language;
import com.learning.babelbox.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository languageRepository;

    @Transactional(readOnly = true)
    public Language getLanguage(String localeCode) {
        return languageRepository.findByLocaleCode(localeCode);
    }

    @Transactional
    public Language createIfMissing(String localCode) {
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
