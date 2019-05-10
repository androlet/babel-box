package com.learning.babelbox.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
@NoArgsConstructor
public class Translation extends EntityCore {

    @ManyToOne
    private Word originalTerm;
    @ManyToOne
    private Signification signification;

    public Translation(Word originalTerm, Signification signification) {
        this.originalTerm = originalTerm;
        this.signification = signification;
    }

    public Word getOriginalTerm() {
        return originalTerm;
    }

    public Signification getSignification() {
        return signification;
    }
}
