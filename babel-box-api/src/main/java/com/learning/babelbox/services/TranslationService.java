package com.learning.babelbox.services;

import com.learning.babelbox.connectors.TranslationConnector;
import com.learning.babelbox.connectors.dto.ConnectorSearchResult;
import com.learning.babelbox.domain.*;
import com.learning.babelbox.exceptions.ReversedLanguageException;
import com.learning.babelbox.exceptions.WrongLanguageException;
import com.learning.babelbox.features.dto.QcmExercise;
import com.learning.babelbox.features.dto.TranslationResults;
import com.learning.babelbox.repository.TranslationRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Service
public class TranslationService {

    private final TranslationRepository translationRepository;
    private final WordService wordService;
    private final SignificationService significationService;
    private final SentenceTranslationService sentenceTranslationService;
    private final TranslationConnector translationConnector;

    public TranslationService(
            TranslationRepository translationRepository,
            WordService wordService,
            SignificationService significationService,
            SentenceTranslationService sentenceTranslationService,
            TranslationConnector translationConnector
    ) {
        this.translationRepository = translationRepository;
        this.wordService = wordService;
        this.significationService = significationService;
        this.sentenceTranslationService = sentenceTranslationService;
        this.translationConnector = translationConnector;
    }

    @Transactional
    public List<Translation> searchTranslations(Language source, Language target, String searchedTerm) {
        List<Translation> translations = translationRepository.findByOriginalTerm(searchedTerm);
        if(!translations.isEmpty()) {
            return translations;
        }
        return fetchAndSaveTranslations(source, target, searchedTerm);
    }

    private List<Translation> fetchAndSaveTranslations(Language source, Language target, String searchedTerm) {
        ConnectorSearchResult searchResult = translationConnector.fetch(source, target, searchedTerm);

        if (hasReversedLanguage(source, target, searchResult)) {
            throw new ReversedLanguageException();
        } else if (hasWrongLanguage(source, target, searchResult)) {
            throw new WrongLanguageException();
        }

        Word originalTerm = wordService.create(
                new Word(source, searchResult.getOriginalTerm(), searchResult.getOriginalTermPronunciation())
        );
        return searchResult.getResultList().stream()
                .flatMap(result -> result.getSignifications().stream())
                .map(resultSignification -> {
                    Signification signification = significationService.create(new Signification(target, resultSignification.getDescription()));
                    SentenceTranslation example = null;
                    if (resultSignification.hasAnExample()) {
                        Sentence originalExample = new Sentence(source, resultSignification.getOriginalLanguageExample());
                        Sentence resultExample = new Sentence(target, resultSignification.getResultLanguageExample());
                        example = sentenceTranslationService.getOrCreate(originalExample, resultExample);
                    }
                    return translationRepository.save(new Translation(originalTerm, signification, example));
                })
                .collect(Collectors.toList());
    }

    private boolean hasReversedLanguage(Language source, Language target, ConnectorSearchResult searchResult) {
        return source.getLocaleCode().equals(searchResult.getTo()) && target.getLocaleCode().equals(searchResult.getFrom());
    }

    private boolean hasWrongLanguage(Language source, Language target, ConnectorSearchResult searchResult) {
        return !source.getLocaleCode().equals(searchResult.getFrom()) || !target.getLocaleCode().equals(searchResult.getTo());
    }
}
