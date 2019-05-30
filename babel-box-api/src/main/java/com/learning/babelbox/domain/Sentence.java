package com.learning.babelbox.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Sentence extends EntityCore {

    @ManyToOne
    private Language language;
    private String content;

    public Sentence() {
    }

    public Sentence(Language language, String content) {
        this.language = language;
        this.content = content;
    }

    public Language getLanguage() {
        return language;
    }

    public String getContent() {
        return content;
    }
}
