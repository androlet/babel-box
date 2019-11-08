package com.learning.babelbox.services;

import com.learning.babelbox.connectors.dto.ConnectorSearchResult;
import com.learning.babelbox.domain.*;
import com.learning.babelbox.repository.TranslationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TranslationService {

    private final TranslationRepository translationRepository;
    private final WordService wordService;
    private final SignificationService significationService;
    private final SentenceTranslationService sentenceTranslationService;

    public TranslationService(
            TranslationRepository translationRepository,
            WordService wordService,
            SignificationService significationService,
            SentenceTranslationService sentenceTranslationService
    ) {
        this.translationRepository = translationRepository;
        this.wordService = wordService;
        this.significationService = significationService;
        this.sentenceTranslationService = sentenceTranslationService;
    }

    @Transactional(readOnly = true)
    public List<Translation> findByTerm(Language source, Language target, String term) {
        return translationRepository.findByOriginalTerm(source, target, term);
    }

    @Transactional
    public List<Translation> create(Language source, Language target, ConnectorSearchResult searchResult) {
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
}
