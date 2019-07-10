package com.learning.babelbox.services;

import com.learning.babelbox.domain.Sentence;
import com.learning.babelbox.domain.SentenceTranslation;
import com.learning.babelbox.repository.SentenceTranslationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SentenceTranslationService {

    private final SentenceTranslationRepository sentenceTranslationRepository;
    private final SentenceService sentenceService;

    public SentenceTranslationService(SentenceTranslationRepository sentenceTranslationRepository, SentenceService sentenceService) {
        this.sentenceTranslationRepository = sentenceTranslationRepository;
        this.sentenceService = sentenceService;
    }

    @Transactional
    public SentenceTranslation getOrCreate(Sentence original, Sentence target) {
        Sentence originalSaved = sentenceService.getOrCreate(original);
        Sentence targetSaved = sentenceService.getOrCreate(target);
        return sentenceTranslationRepository.findByOriginalSentenceAndTargetSentence(originalSaved, targetSaved)
                .orElseGet(() -> sentenceTranslationRepository.save(new SentenceTranslation(originalSaved, targetSaved)));
    }
}
