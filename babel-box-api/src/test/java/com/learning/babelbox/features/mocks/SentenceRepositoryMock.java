package com.learning.babelbox.features.mocks;

import com.learning.babelbox.domain.Language;
import com.learning.babelbox.domain.Sentence;
import com.learning.babelbox.mocks.BaseRepositoryMock;
import com.learning.babelbox.repository.SentenceRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SentenceRepositoryMock extends BaseRepositoryMock<Sentence> implements SentenceRepository {

    public SentenceRepositoryMock() {
        super(Sentence.class);
    }

    @Override
    public Optional<Sentence> findByLanguageAndContent(Language language, String content) {
        return getRepositoryData().values().stream().filter(
                s -> s.getLanguage().getId().equals(language.getId()) &&
                        s.getContent().equals(content)
        ).findFirst();
    }
}
