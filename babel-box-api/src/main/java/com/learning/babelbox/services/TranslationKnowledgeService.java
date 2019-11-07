package com.learning.babelbox.services;

import com.learning.babelbox.domain.Language;
import com.learning.babelbox.domain.TranslationKnowledge;
import com.learning.babelbox.domain.User;
import com.learning.babelbox.exceptions.UnavailableExerciseException;
import com.learning.babelbox.features.dto.QcmExercise;
import com.learning.babelbox.platform.RandomProvider;
import com.learning.babelbox.repository.TranslationKnowledgeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;


@Service
public class TranslationKnowledgeService {

    private final static int NUMBER_OF_QCM_OPTIONS = 4;
    private final static int LESS_REMAINED_TRANSLATION_KNOWLEDGE_SAMPLE_SIZE = 20;

    private final RandomProvider randomProvider;
    private final TranslationKnowledgeRepository translationKnowledgeRepository;
    private final UserService userService;

    public TranslationKnowledgeService(RandomProvider randomProvider, TranslationKnowledgeRepository translationKnowledgeRepository, UserService userService) {
        this.randomProvider = randomProvider;
        this.translationKnowledgeRepository = translationKnowledgeRepository;
        this.userService = userService;
    }

    @Transactional
    public QcmExercise generateQcmExercise(Language source, Language target) {
        User currentUser = userService.current();
        List<TranslationKnowledge> sample = translationKnowledgeRepository.findMostLessRemainedTranslationKnowledges(
                source, target, currentUser, PageRequest.of(0, LESS_REMAINED_TRANSLATION_KNOWLEDGE_SAMPLE_SIZE));
        List<TranslationKnowledge> options = getOptionsTranslationKnowledge(sample);
        int rightAnswerIndex = randomProvider.getRandomNumber(0, NUMBER_OF_QCM_OPTIONS - 1);
        return new QcmExercise(
                adjustsQcmRemainings(options, rightAnswerIndex),
                rightAnswerIndex
        );
    }

    private List<TranslationKnowledge> adjustsQcmRemainings(List<TranslationKnowledge> options, int rightAnswerIndex) {
        options.add(rightAnswerIndex, remainTranslationKnowledge(options.remove(rightAnswerIndex)));
        return options;
    }

    private TranslationKnowledge remainTranslationKnowledge(TranslationKnowledge translationKnowledge) {
        translationKnowledge.increaseRemainingTimes();
        return translationKnowledgeRepository.save(translationKnowledge);
    }

    private List<TranslationKnowledge> getOptionsTranslationKnowledge(List<TranslationKnowledge> sample) {
        List<TranslationKnowledge> options = new ArrayList<>();
        if (sample.size() < NUMBER_OF_QCM_OPTIONS) {
            throw new UnavailableExerciseException(
                    format("Missing sufficient number of user's translations (search results) to generate a QCM" +
                            " ( %d / %d options only).", sample.size(), NUMBER_OF_QCM_OPTIONS)
            );
        }
        for (int c = 0; c < NUMBER_OF_QCM_OPTIONS; c++) {
            options.add((TranslationKnowledge) randomProvider.removeRandomElement(sample));
        }

        return options;
    }
}
