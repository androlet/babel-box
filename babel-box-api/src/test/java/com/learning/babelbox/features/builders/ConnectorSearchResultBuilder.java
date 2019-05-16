package com.learning.babelbox.features.builders;

import com.learning.babelbox.connectors.dto.ConnectorSearchResult;

public class ConnectorSearchResultBuilder {

    public static ConnectorSearchResult.Signification significationFrom(String description, String originalExampleLanguage, String resultExampleLanguage) {
        ConnectorSearchResult.Signification signification = new ConnectorSearchResult.Signification(description);
        signification.setOriginalLanguageExample(originalExampleLanguage);
        signification.setResultLanguageExample(resultExampleLanguage);
        return signification;
    }
}
