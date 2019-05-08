package com.learning.babelbox.connectors.dto;

import java.util.List;

public class ConnectorSearchResult {
    private String originalTerm;
    private String originalTermPronunciation;
    private List<String> resultList;

    public ConnectorSearchResult(String originalTerm, String originalTermPronunciation, List<String> resultList) {
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

    public List<String> getResultList() {
        return resultList;
    }
}
