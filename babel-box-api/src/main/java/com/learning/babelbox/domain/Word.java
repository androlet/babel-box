package com.learning.babelbox.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Word extends EntityCore {

    @ManyToOne
    private Language language;
    private String spelling;
    private String pronunciation;

    public Word() {}

    public Word(Language language, String spelling) {
        this.language = language;
        this.spelling = spelling;
    }

    public Word(Language language, String spelling, String pronunciation) {
        this(language, spelling);
        this.pronunciation = pronunciation;
    }

    public Language getLanguage() {
        return language;
    }

    public String getSpelling() {
        return spelling;
    }

    public String getPronunciation() {
        return pronunciation;
    }
}
