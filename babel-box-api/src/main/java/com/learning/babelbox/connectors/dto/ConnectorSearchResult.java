package com.learning.babelbox.connectors.dto;

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

        private List<String> significations;
        private List<String> originalLanguageExamples;
        private List<String> resultLanguageExamples;

        public Result(List<String> significations, List<String> originalLanguageExamples, List<String> resultLanguageExamples) {
            this.significations = significations;
            this.originalLanguageExamples = originalLanguageExamples;
            this.resultLanguageExamples = resultLanguageExamples;
        }

        public List<String> getSignifications() {
            return significations;
        }

        public List<String> getOriginalLanguageExamples() {
            return originalLanguageExamples;
        }

        public List<String> getResultLanguageExamples() {
            return resultLanguageExamples;
        }
    }
}
