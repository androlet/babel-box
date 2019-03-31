package com.learning.babelbox.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
public class Word extends EntityCore {

    @ManyToOne
    private Language language;
    private String spelling;
    private String pronunciation;

    public Word(Language language, String spelling) {
        this.language = language;
        this.spelling = spelling;
    }

    public Word(Language language, String spelling, String pronunciation) {
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
