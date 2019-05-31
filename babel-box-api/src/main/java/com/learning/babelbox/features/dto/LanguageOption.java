package com.learning.babelbox.features.dto;

import com.learning.babelbox.domain.Language;

import java.util.List;
import java.util.stream.Collectors;

public class LanguageOption {
    private Long id;
    private String code;

    public static LanguageOption from(Language language) {
        LanguageOption languageOption = new LanguageOption();
        languageOption.setId(language.getId());
        languageOption.setCode(language.getLocaleCode());
        return languageOption;
    }

    public static List<LanguageOption> from(List<Language> languages) {
        return languages.stream().map(l -> from(l)).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
