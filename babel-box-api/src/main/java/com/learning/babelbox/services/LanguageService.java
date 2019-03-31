package com.learning.babelbox.services;

import com.learning.babelbox.domain.Language;
import com.learning.babelbox.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository languageRepository;

    public void initLanguages(String... localeCodes) {
        for(String localeCode : localeCodes) {
            createIfMissing(localeCode);
        }
    }

    public Language getLanguage(String localeCode) {
        return languageRepository.findByLocaleCode(localeCode);
    }

    private Language createIfMissing(String localCode) {
        Language language = getLanguage(localCode);
        if(null == language) {
            language = create(localCode);
        }
        return language;
    }

    private Language create(String localeCode) {
        return languageRepository.save(new Language(localeCode));
    }
}
