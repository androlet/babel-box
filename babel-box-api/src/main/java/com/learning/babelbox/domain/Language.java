package com.learning.babelbox.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Language extends EntityCore {
    public Language() {}

    @Column(name = "local_code")
    private String localeCode;

    public Language(String localeCode) {
        this.localeCode = localeCode;
    }

    public String getLocaleCode() {
        return localeCode;
    }
}
