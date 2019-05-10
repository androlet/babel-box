package com.learning.babelbox.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
public class Signification extends EntityCore {

    @ManyToOne
    private Language language;
    private String description;

    public Signification(Language language, String description) {
        this.language = language;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
