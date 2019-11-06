package com.learning.babelbox.services;

import com.learning.babelbox.domain.Language;
import com.learning.babelbox.domain.Translation;
import com.learning.babelbox.repository.TranslationKnowledgeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TranslationKnowledgeService {

    private final TranslationKnowledgeRepository translationKnowledgeRepository;
    private final UserService userService;

    public TranslationKnowledgeService(TranslationKnowledgeRepository translationKnowledgeRepository, UserService userService) {
        this.translationKnowledgeRepository = translationKnowledgeRepository;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public List<Translation> getTranslations(Language source, Language target) {
        List<Translation> translations = null;// = translationRepository;
        return translations;
    }
}
