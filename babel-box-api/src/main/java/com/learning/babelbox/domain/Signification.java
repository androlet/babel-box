package com.learning.babelbox.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Signification extends EntityCore {

    @ManyToOne
    private Language language;
    private String description;

    public Signification() {}

    public Signification(Language language, String description) {
        this.language = language;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
