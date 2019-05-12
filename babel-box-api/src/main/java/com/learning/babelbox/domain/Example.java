package com.learning.babelbox.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Example extends EntityCore {

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
