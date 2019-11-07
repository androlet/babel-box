package com.learning.babelbox.features.mocks;

import com.learning.babelbox.domain.Language;
import com.learning.babelbox.mocks.BaseRepositoryMock;
import com.learning.babelbox.repository.LanguageRepository;
import org.springframework.stereotype.Component;

@Component
public class LanguageRepositoryMock extends BaseRepositoryMock<Language> implements LanguageRepository {

    public LanguageRepositoryMock() {
        super(Language.class);
    }

    @Override
    public Language findByLocaleCode(String localeCode) {
        return getRepositoryData().values().stream()
                .filter(language -> language.getLocaleCode().equals(localeCode))
                .findFirst().orElse(null);
    }
}
