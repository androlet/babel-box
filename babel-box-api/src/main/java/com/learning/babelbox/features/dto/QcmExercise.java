package com.learning.babelbox.features.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learning.babelbox.domain.Translation;

import java.util.List;

import static java.util.Arrays.asList;

public class QcmExercise {

    private String qcmQuestion;
    private List<QcmOption> options;

    public QcmExercise(List<QcmOption> options) {
        this.qcmQuestion = qcmQuestion;
        this.options = options;
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

        public QcmOption(Translation translation) {
            this.content = translation.getSignification().getDescription();
            this.translation = new TranslationResults(asList(translation));
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
