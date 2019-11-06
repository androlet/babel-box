package com.learning.babelbox.domain;

import javax.persistence.Entity;

@Entity
public class TranslationKnowledge extends EntityCore {

    private Translation translation;
    private User user;

    public TranslationKnowledge(Translation translation, User user) {
        this.translation = translation;
        this.user = user;
    }
}
