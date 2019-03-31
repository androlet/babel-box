package com.learning.babelbox.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Language extends EntityCore {

    private String localeCode;

    public Language(String localeCode) {
        this.localeCode = localeCode;
    }
}
