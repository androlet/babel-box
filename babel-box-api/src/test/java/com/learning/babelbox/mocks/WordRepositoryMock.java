package com.learning.babelbox.mocks;

import com.learning.babelbox.domain.Word;
import com.learning.babelbox.repository.WordRepository;
import org.springframework.stereotype.Component;

@Component
public class WordRepositoryMock extends BaseRepositoryMock<Word> implements WordRepository {
}
