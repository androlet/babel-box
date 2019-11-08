package com.learning.babelbox.features.mocks;

import com.learning.babelbox.domain.Language;
import com.learning.babelbox.domain.Translation;
import com.learning.babelbox.mocks.BaseRepositoryMock;
import com.learning.babelbox.repository.TranslationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TranslationRepositoryMock extends BaseRepositoryMock<Translation> implements TranslationRepository {

    public TranslationRepositoryMock() {
        super(Translation.class);
    }

    @Override
    public List<Translation> findByOriginalTerm(Language source, Language target, String originalTerm) {
        return getRepositoryData().values().stream()
                .filter(translation -> translation.getOriginalTerm().getSpelling().equals(originalTerm)
                        && translation.getOriginalTerm().getLanguage().getId().equals(source.getId())
                        && translation.getSignification().getLanguage().getId().equals(target.getId()))
                .collect(Collectors.toList());
    }
}
