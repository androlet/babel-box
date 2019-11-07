package com.learning.babelbox.features.mocks;

import com.learning.babelbox.domain.Sentence;
import com.learning.babelbox.domain.SentenceTranslation;
import com.learning.babelbox.mocks.BaseRepositoryMock;
import com.learning.babelbox.repository.SentenceTranslationRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SentenceTranslationRepositoryMock extends BaseRepositoryMock<SentenceTranslation> implements SentenceTranslationRepository {

    public SentenceTranslationRepositoryMock() {
        super(SentenceTranslation.class);
    }

    @Override
    public Optional<SentenceTranslation> findByOriginalSentenceAndTargetSentence(Sentence original, Sentence target) {
        return getRepositoryData().values().stream().filter(
                st -> st.getOriginalSentence().getId().equals(original.getId()) &&
                        st.getTargetSentence().getId().equals(target.getId())
        ).findFirst();
    }
}
