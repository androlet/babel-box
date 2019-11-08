package com.learning.babelbox.services;

import com.learning.babelbox.connectors.TranslationConnector;
import com.learning.babelbox.connectors.dto.ConnectorSearchResult;
import com.learning.babelbox.domain.Language;
import com.learning.babelbox.domain.Translation;
import com.learning.babelbox.domain.TranslationKnowledge;
import com.learning.babelbox.exceptions.ReversedLanguageException;
import com.learning.babelbox.exceptions.WrongLanguageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final TranslationKnowledgeService translationKnowledgeService;
    private final TranslationService translationService;
    private final TranslationConnector translationConnector;

    public SearchService(TranslationKnowledgeService translationKnowledgeService, TranslationService translationService, TranslationConnector translationConnector) {
        this.translationKnowledgeService = translationKnowledgeService;
        this.translationService = translationService;
        this.translationConnector = translationConnector;
    }

    @Transactional
    public List<Translation> searchTranslations(Language source, Language target, String searchedTerm) {
        List<Translation> userKnownTranslations = translationKnowledgeService.findByUserAndTerm(source, target, searchedTerm)
                .stream()
                .map(TranslationKnowledge::getTranslation)
                .collect(Collectors.toList());

        if(!userKnownTranslations.isEmpty()) {
            return userKnownTranslations;
        }

        List<Translation> storedTranslations = translationService.findByTerm(source, target, searchedTerm);
        if(!storedTranslations.isEmpty()) {
            translationKnowledgeService.create(storedTranslations);
            return storedTranslations;
        }

        return fetchAndSaveTranslationsKnowledge(source, target, searchedTerm);
    }

    private List<Translation> fetchAndSaveTranslationsKnowledge(Language source, Language target, String searchedTerm) {
        ConnectorSearchResult searchResult = translationConnector.fetch(source, target, searchedTerm);

        if (hasReversedLanguage(source, target, searchResult)) {
            throw new ReversedLanguageException();
        } else if (hasWrongLanguage(source, target, searchResult)) {
            throw new WrongLanguageException();
        }

        List<Translation> persistedTranslations = translationService.create(source, target, searchResult);
        translationKnowledgeService.create(persistedTranslations);
        return persistedTranslations;
    }

    private boolean hasReversedLanguage(Language source, Language target, ConnectorSearchResult searchResult) {
        return source.getLocaleCode().equals(searchResult.getTo()) && target.getLocaleCode().equals(searchResult.getFrom());
    }

    private boolean hasWrongLanguage(Language source, Language target, ConnectorSearchResult searchResult) {
        return !source.getLocaleCode().equals(searchResult.getFrom()) || !target.getLocaleCode().equals(searchResult.getTo());
    }
}
