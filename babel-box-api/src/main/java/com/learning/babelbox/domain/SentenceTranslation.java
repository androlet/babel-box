package com.learning.babelbox.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class SentenceTranslation extends EntityCore {

    @ManyToOne
    private Sentence originalSentence;
    @ManyToOne
    private Sentence targetSentence;

    public SentenceTranslation() {
    }

    public SentenceTranslation(Sentence originalSentence, Sentence targetSentence) {
        this.originalSentence = originalSentence;
        this.targetSentence = targetSentence;
    }

    public Sentence getOriginalSentence() {
        return originalSentence;
    }

    public Sentence getTargetSentence() {
        return targetSentence;
    }
}
