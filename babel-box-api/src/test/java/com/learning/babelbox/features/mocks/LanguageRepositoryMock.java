package com.learning.babelbox.features.mocks;

import com.learning.babelbox.domain.Language;
import com.learning.babelbox.repository.LanguageRepository;
import org.springframework.stereotype.Component;

@Component
public class LanguageRepositoryMock extends BaseRepositoryMock<Language> implements LanguageRepository {

    @Override
    public Language findByLocaleCode(String localeCode) {
        return data.values().stream()
                .filter(language -> language.getLocaleCode().equals(localeCode))
                .findFirst().get();
    }
}
