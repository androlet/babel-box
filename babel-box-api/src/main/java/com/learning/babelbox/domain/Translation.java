package com.learning.babelbox.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Translation extends EntityCore {

    @ManyToOne
    private Word originalTerm;
    @OneToOne
    private Signification signification;
    @ManyToOne
    private SentenceTranslation example;

    public Translation() {}

    public Translation(Word originalTerm, Signification signification) {
        this.originalTerm = originalTerm;
        this.signification = signification;
    }

    public Translation(Word originalTerm, Signification signification, SentenceTranslation example) {
        this(originalTerm, signification);
        this.example = example;
    }

    public Word getOriginalTerm() {
        return originalTerm;
    }

    public Signification getSignification() {
        return signification;
    }

    public String getOriginalExample() {
        return example == null ? null : example.getOriginalSentence().getContent();
    }

    public String getTranslatedExample() {
        return example == null ? null : example.getTargetSentence().getContent();
    }
}
