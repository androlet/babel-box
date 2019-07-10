package com.learning.babelbox.services;

import com.learning.babelbox.domain.Word;
import com.learning.babelbox.repository.WordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WordService {

    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Transactional
    public Word create(Word word) {
        return wordRepository.save(word);
    }
}
