package com.learning.babelbox.features.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learning.babelbox.domain.TranslationKnowledge;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class QcmExercise {

    private String qcmQuestion;
    private List<QcmOption> options;

    private QcmExercise(List<TranslationKnowledge> options) {
        this.options = options.stream().map(QcmOption::new).collect(toList());
    }

    public QcmExercise(List<TranslationKnowledge> options, int answerIndex) {
        this(options);
        setRightAnswer(answerIndex);
    }

    public void setRightAnswer(int index) {
        QcmOption rightAnswer = this.options.get(index);
        rightAnswer.setRightAnswer(true);
        this.qcmQuestion = rightAnswer.translation.getOriginalTerm();
    }

    public String getQcmQuestion() {
        return qcmQuestion;
    }

    public List<QcmOption> getOptions() {
        return options;
    }

    public static class QcmOption {

        public QcmOption(TranslationKnowledge translationKnowledge) {
            this.content = translationKnowledge.getTranslation().getSignification().getDescription();
            this.translation = new TranslationResults(asList(translationKnowledge.getTranslation()));
            this.isRightAnswer = false;
        }

        private String content;
        private TranslationResults translation;
        private boolean isRightAnswer;
        private boolean isUserAnswer;

        public String getContent() {
            return content;
        }

        public TranslationResults getTranslation() {
            return translation;
        }

        @JsonProperty("isRightAnswer")
        public boolean isRightAnswer() {
            return isRightAnswer;
        }

        public void setRightAnswer(boolean rightAnswer) {
            isRightAnswer = rightAnswer;
        }
    }
}
