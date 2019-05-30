package com.learning.babelbox.connectors.dto;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ConnectorSearchResult {
    private String originalTerm;
    private String originalTermPronunciation;
    private List<Result> resultList;

    public ConnectorSearchResult(String originalTerm, String originalTermPronunciation, List<Result> resultList) {
        this.originalTerm = originalTerm;
        this.originalTermPronunciation = originalTermPronunciation;
        this.resultList = resultList;
    }

    public String getOriginalTerm() {
        return originalTerm;
    }

    public String getOriginalTermPronunciation() {
        return originalTermPronunciation;
    }

    public List<Result> getResultList() {
        return resultList;
    }

    public static class Result {

        private List<Signification> significations;

        public Result(List<Signification> significations) {
            this.significations = significations;
        }

        public List<Signification> getSignifications() {
            return significations;
        }
    }



    public static class Signification {
        private String description;
        private String originalLanguageExample;
        private String resultLanguageExample;

        public Signification(String description) {
            this.description = description;
        }

        public void setOriginalLanguageExample(String originalLanguageExample) {
            this.originalLanguageExample = originalLanguageExample;
        }

        public void setResultLanguageExample(String resultLanguageExample) {
            this.resultLanguageExample = resultLanguageExample;
        }

        public String getDescription() {
            return description;
        }

        public boolean hasAnExample() {
            return StringUtils.isNotBlank(originalLanguageExample) && StringUtils.isNotBlank(resultLanguageExample);
        }

        public String getOriginalLanguageExample() {
            return originalLanguageExample;
        }

        public String getResultLanguageExample() {
            return resultLanguageExample;
        }
    }
}
