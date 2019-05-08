package com.learning.babelbox.features.mocks;

import com.learning.babelbox.connectors.TranslationConnector;
import com.learning.babelbox.connectors.dto.ConnectorSearchResult;
import com.learning.babelbox.domain.Language;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Primary
public class WordReferenceConnectorMock implements TranslationConnector {

    private Map<String, ConnectorSearchResult> results;

    public WordReferenceConnectorMock() {
        results = new HashMap<>();
    }

    public void setResult(String word, ConnectorSearchResult result) {
        results.put(word, result);
    }

    @Override
    public ConnectorSearchResult fetch(Language source, Language target, String word) {
        return results.get(word);
    }
}
