package com.learning.babelbox.features.dto;

import java.util.List;

public class QuestionExercise {

    private List<TranslationResults> answers;
    private int rightAnswerIndex;

    public QuestionExercise(List<TranslationResults> answers) {
        this.answers = answers;
    }

    public List<TranslationResults> getAnswers() {
        return answers;
    }

    public void setAnswers(List<TranslationResults> answers) {
        this.answers = answers;
    }

    public int getRightAnswerIndex() {
        return rightAnswerIndex;
    }

    public void setRightAnswerIndex(int rightAnswerIndex) {
        this.rightAnswerIndex = rightAnswerIndex;
    }
}
