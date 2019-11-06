package com.learning.babelbox.features.mocks;

import com.learning.babelbox.domain.Translation;
import com.learning.babelbox.mocks.BaseRepositoryMock;
import com.learning.babelbox.repository.TranslationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TranslationRepositoryMock extends BaseRepositoryMock<Translation> implements TranslationRepository {

    @Override
    public List<Translation> findByOriginalTerm(String originalTerm) {
        return data.values().stream()
                .filter(translation -> translation.getOriginalTerm().getSpelling().equals(originalTerm))
                .collect(Collectors.toList());
    }
}
