package com.learning.babelbox.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class TranslationKnowledge extends EntityCore {

    @ManyToOne
    private Translation translation;
    @ManyToOne
    private User user;
    private Long numberOfRemaining;

    public TranslationKnowledge() {}

    public TranslationKnowledge(Translation translation, User user) {
        this.translation = translation;
        this.user = user;
        numberOfRemaining = 0L;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void increaseRemainingTimes() {
        numberOfRemaining++;
    }

    public User getUser() {
        return user;
    }

    public Long getNumberOfRemaining() {
        return numberOfRemaining;
    }
}
