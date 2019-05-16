package com.learning.babelbox.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Example extends EntityCore {

    public Example() {}

    @ManyToOne
    private Language language;
    private String sentence;

    public Example(Language language, String sentence) {
        this.language = language;
        this.sentence = sentence;
    }

    public String getSentence() {
        return sentence;
    }
}
