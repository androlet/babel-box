package com.learning.babelbox.services;

import com.learning.babelbox.domain.Sentence;
import com.learning.babelbox.repository.SentenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SentenceService {

    private final SentenceRepository sentenceRepository;

    public SentenceService(SentenceRepository sentenceRepository) {
        this.sentenceRepository = sentenceRepository;
    }

    @Transactional
    public Sentence getOrCreate(Sentence sentence) {
        return sentenceRepository.findByLanguageAndContent(sentence.getLanguage(), sentence.getContent())
                .orElseGet(() -> sentenceRepository.save(sentence));
    }
}
