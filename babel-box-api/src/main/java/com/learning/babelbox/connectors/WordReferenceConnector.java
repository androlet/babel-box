package com.learning.babelbox.connectors;

import com.learning.babelbox.connectors.dto.ConnectorSearchResult;
import com.learning.babelbox.domain.Language;
import com.learning.babelbox.domain.Translation;
import com.learning.babelbox.domain.Word;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WordReferenceConnector implements TranslationConnector {

    private static final String BASE_URL = "https://www.wordreference.com";

    private final HtmlParser htmlParser;

    private String buildUrl(Language source, Language target, String word) {
        return new StringBuilder(BASE_URL)
                .append("/")
                .append(source.getLocaleCode() + target.getLocaleCode())
                .append("/")
                .append(word)
                .toString();
    }

    private String retrieveText(String rawContent) {
        return StringEscapeUtils.unescapeHtml4(rawContent
                .replaceAll("[\\[\\]]", "")
                .split("<")[0]).trim();
    }

    private String retrieveOriginalWord(Document document) {
        return document.select("h3.headerWord").html();
    }

    private String retrievePrononciation(Document document) {
        return retrieveText(document.select("#pronWR").html());
    }

    private Elements retrieveElementTranslation(Document document) {
        return document.select("tr.odd > td.ToWrd, tr.even > td.ToWrd");
    }

    @Override
    public ConnectorSearchResult fetch(Language source, Language target, String word) {
        String url = buildUrl(source, target, word);
        try {
            Document document = htmlParser.download(url);
            return new ConnectorSearchResult(
                retrieveOriginalWord(document),
                retrievePrononciation(document),
                retrieveElementTranslation(document).subList(0, 10).stream()
                        .map(element -> retrieveText(element.html()))
                        .collect(Collectors.toList())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
