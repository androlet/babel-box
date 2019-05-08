package com.learning.babelbox.mocks;

import com.learning.babelbox.connectors.TranslationConnector;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Primary
public class WordReferenceConnectorMock implements TranslationConnector {

    private Map<String, String> results;

    public WordReferenceConnectorMock() {
        results = new HashMap<>();
    }

    public void setResult(String word, String result) {
        results.put(word, result);
    }

    @Override
    public String fetch(String word) {
        return null;
    }
}
