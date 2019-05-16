package com.learning.babelbox.domain;

import javax.persistence.*;


@Entity
public class Translation extends EntityCore {

    @ManyToOne
    private Word originalTerm;
    @OneToOne @MapsId
    private Signification signification;
    @OneToOne
    private Example originalExample;
    @OneToOne
    private Example translatedExample;

    public Translation() {}

    public Translation(Word originalTerm, Signification signification) {
        this.originalTerm = originalTerm;
        this.signification = signification;
    }

    public Translation(Word originalTerm, Signification signification, Example originalExample, Example translatedExample) {
        this(originalTerm, signification);
        this.originalExample = originalExample;
        this.translatedExample = translatedExample;
    }

    public Word getOriginalTerm() {
        return originalTerm;
    }

    public Signification getSignification() {
        return signification;
    }

    public Example getOriginalExample() {
        return originalExample;
    }

    public Example getTranslatedExample() {
        return translatedExample;
    }
}
