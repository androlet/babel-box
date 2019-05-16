package com.learning.babelbox.services;

import com.learning.babelbox.connectors.TranslationConnector;
import com.learning.babelbox.connectors.dto.ConnectorSearchResult;
import com.learning.babelbox.domain.*;
import com.learning.babelbox.features.dto.TranslationResults;
import com.learning.babelbox.repository.TranslationRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TranslationService {

    private final TranslationRepository translationRepository;
    private final LanguageService languageService;
    private final WordService wordService;
    private final SignificationService significationService;
    private final ExampleService exampleService;
    private final TranslationConnector translationConnector;

    public TranslationService(TranslationRepository translationRepository, LanguageService languageService, WordService wordService, SignificationService significationService, ExampleService exampleService, TranslationConnector translationConnector) {
        this.translationRepository = translationRepository;
        this.languageService = languageService;
        this.wordService = wordService;
        this.significationService = significationService;
        this.exampleService = exampleService;
        this.translationConnector = translationConnector;
    }

    @Transactional
    public TranslationResults getTranslationResults(Language source, Language target, String searchedTerm) {
        List<Translation> translations = translationRepository.findByOriginalTerm(searchedTerm);
        if(!translations.isEmpty()) {
            return new TranslationResults(translations);
        }
        return new TranslationResults(fetchAndSaveTranslations(source, target, searchedTerm));
    }

    @Transactional
    public List<Translation> fetchAndSaveTranslations(Language source, Language target, String searchedTerm) {
        ConnectorSearchResult searchResult = translationConnector.fetch(source, target, searchedTerm);
        Word originalTerm = wordService.create(
                new Word(source, searchResult.getOriginalTerm(), searchResult.getOriginalTermPronunciation())
        );
        return searchResult.getResultList().stream()
                .flatMap(result -> result.getSignifications().stream())
                .map(resultSignification -> {
                    Signification signification = significationService.create(new Signification(target, resultSignification.getDescription()));
                    Example originalExample = null;
                    Example resultExample = null;
                    if (StringUtils.isNotBlank(resultSignification.getOriginalLanguageExample())) {
                        originalExample = exampleService.create(new Example(source, resultSignification.getOriginalLanguageExample()) );
                        resultExample = exampleService.create(new Example(target, resultSignification.getResultLanguageExample()));
                    }
                    return translationRepository.save(new Translation(originalTerm, signification, originalExample, resultExample));
                })
                .collect(Collectors.toList());
    }
}
