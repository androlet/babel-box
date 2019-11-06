package com.learning.babelbox.features.mocks;

import com.learning.babelbox.domain.TranslationKnowledge;
import com.learning.babelbox.mocks.BaseRepositoryMock;
import com.learning.babelbox.repository.TranslationKnowledgeRepository;
import org.springframework.stereotype.Component;

@Component
public class TranslationKnowledgeRepositoryMock extends BaseRepositoryMock<TranslationKnowledge> implements TranslationKnowledgeRepository {

}
