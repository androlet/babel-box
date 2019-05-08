package com.learning.babelbox.connectors;

import com.learning.babelbox.connectors.dto.ConnectorSearchResult;
import com.learning.babelbox.domain.Language;

public interface TranslationConnector {
    ConnectorSearchResult fetch(Language source, Language target, String word);
}
