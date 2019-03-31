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
    private Word translatedTerm;

    public Translation(Word originalTerm, Word translatedTerm) {
        this.originalTerm = originalTerm;
        this.translatedTerm = translatedTerm;
    }

    public Word getOriginalTerm() {
        return originalTerm;
    }

    public Word getTranslatedTerm() {
        return translatedTerm;
    }
}
