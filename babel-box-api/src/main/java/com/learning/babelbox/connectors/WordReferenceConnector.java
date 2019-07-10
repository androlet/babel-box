package com.learning.babelbox.connectors;

import com.learning.babelbox.connectors.dto.ConnectorSearchResult;
import com.learning.babelbox.domain.Language;
import com.learning.babelbox.platform.HtmlParser;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordReferenceConnector implements TranslationConnector {

    private static final String BASE_URL = "https://www.wordreference.com";

    private final HtmlParser htmlParser;

    public WordReferenceConnector(HtmlParser htmlParser) {
        this.htmlParser = htmlParser;
    }

    private String buildUrl(Language source, Language target, String word) {
        return new StringBuilder(BASE_URL)
                .append("/")
                .append(source.getLocaleCode() + target.getLocaleCode())
                .append("/")
                .append(word)
                .toString();
    }

    private boolean isValidResult(Element elementTranslation) {
        return !elementTranslation.children().get(2).text().equals("-");
    }

    private String retrieveText(String rawContent) {
        return StringEscapeUtils.unescapeHtml4(rawContent
                .replaceAll("[\\[\\]]", "")
                .split("<")[0]).trim();
    }

    private String retrieveFrom(Document document) {
        return document.select("label.custom-select > select > option").first().attr("value").substring(0, 2);
    }

    private String retrieveTo(Document document) {
        return document.select("label.custom-select > select > option").first().attr("value").substring(2);
    }

    private String retrieveOriginalWord(Document document) {
        return document.select("h3.headerWord").html();
    }

    private String retrievePrononciation(Document document) {
        return retrieveText(document.select("#pronWR").html());
    }

    private boolean isElementASignification(Element element) {
        return !element.select("td.ToWrd").isEmpty();
    }

    private String retrieveSignification(Element element) {
        return retrieveText(element.select("td.ToWrd").html());
    }

    private boolean isElementAnOriginalExample(Element element) {
        Elements search = element.select("td.FrEx");
        return !search.isEmpty() && !search.html().contains("ⓘ");
    }

    private String retrieveOriginalExample(Element element) {
        return retrieveText(element.select("td.FrEx").html());
    }

    private boolean isElementAResultExample(Element element) {
        Elements search = element.select("td.ToEx");
        return !search.isEmpty() && !search.html().contains("ⓘ");
    }

    private String retrieveResultExample(Element element) {
        return retrieveText(element.select("td.ToEx").html());
    }

    private List<Element> retrieveElementTranslation(Document document) {
        return document.select("table.WRD").first()
                .select("tr.odd > td.FrWrd, tr.even > td.FrWrd")
                .stream()
                .map(Element::parent)
                .filter(this::isValidResult)
                .collect(Collectors.toList());
    }

    private ConnectorSearchResult.Result createResultFromElementTranslation(Element element) {
        int examplesCounter = 0;
        List<ConnectorSearchResult.Signification> significations = new ArrayList<>();
        String lastOriginalExample = null;
        String currentTranslationWordreferenceClass = element.className();
        Element target = element;
        while (null != target && currentTranslationWordreferenceClass.equals(target.className())) {
            if (isElementASignification(target)){
                significations.add(new ConnectorSearchResult.Signification(retrieveSignification(target)));
                lastOriginalExample = null;
            } else if (isElementAnOriginalExample(target)) {
                lastOriginalExample = retrieveOriginalExample(target);
            } else if (null != lastOriginalExample && isElementAResultExample(target)) {
                significations.get(examplesCounter).setOriginalLanguageExample(lastOriginalExample);
                significations.get(examplesCounter).setResultLanguageExample(retrieveResultExample(target));
                examplesCounter++;
            }
            target = target.nextElementSibling();
        }
        return new ConnectorSearchResult.Result(significations);
    }

    @Override
    public ConnectorSearchResult fetch(Language source, Language target, String word) {
        String url = buildUrl(source, target, word);
        try {
            Document document = htmlParser.download(url);
            return new ConnectorSearchResult(
                retrieveFrom(document),
                retrieveTo(document),
                retrieveOriginalWord(document),
                retrievePrononciation(document),
                retrieveElementTranslation(document).stream()
                    .map(this::createResultFromElementTranslation)
                    .collect(Collectors.toList())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
