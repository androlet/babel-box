package com.learning.babelbox.services;

import com.learning.babelbox.domain.Language;
import com.learning.babelbox.domain.Word;
import com.learning.babelbox.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;

    public Word create(Language language, String spelling) {
        return wordRepository.save(new Word(language, spelling));
    }
}
