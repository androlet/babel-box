package com.learning.babelbox.domain;

import javax.persistence.Entity;

@Entity
public class TranslationKnowledge extends EntityCore {

    private Translation translation;
    private User user;

}
