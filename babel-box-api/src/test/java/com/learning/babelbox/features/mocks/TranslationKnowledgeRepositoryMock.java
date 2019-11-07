package com.learning.babelbox.features.mocks;

import com.learning.babelbox.domain.Language;
import com.learning.babelbox.domain.TranslationKnowledge;
import com.learning.babelbox.domain.User;
import com.learning.babelbox.mocks.BaseRepositoryMock;
import com.learning.babelbox.repository.TranslationKnowledgeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TranslationKnowledgeRepositoryMock extends BaseRepositoryMock<TranslationKnowledge> implements TranslationKnowledgeRepository {

    @Override
    public List<TranslationKnowledge> findMostLessRemainedTranslationKnowledges(Language source, Language target, User user, Pageable pageable) {
        Set<String> originalTerms = new HashSet<>();
        List<TranslationKnowledge> results = new ArrayList<>();
        data.values().stream()
                .filter(translationKnowledge ->
                        translationKnowledge.getTranslation().getOriginalTerm().getLanguage().getId().equals(source.getId())
                                && translationKnowledge.getTranslation().getSignification().getLanguage().getId().equals(target.getId())
                                && translationKnowledge.getUser().getId().equals(user.getId())
                ).sorted((one,two) ->
                (int) (two.getNumberOfRemaining() - one.getNumberOfRemaining())
        ).forEach(translationKnowledge -> {
            String originalTerm = translationKnowledge.getTranslation().getOriginalTerm().getSpelling();
            if(!originalTerms.contains(originalTerm)) {
                originalTerms.add(originalTerm);
                results.add(translationKnowledge);
            }
        });
        int limit = pageable.getPageSize();
        return results.size() > limit ? results.subList(0, limit) : results;
    }
}
