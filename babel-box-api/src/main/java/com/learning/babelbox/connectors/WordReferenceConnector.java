package com.learning.babelbox.connectors;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class WordReferenceConnector implements TranslationConnector {

    private final String baseUrl = "http://www.wordreference.com";
    private final String langs = "enfr";

    @Override
    public String fetch(String word) {
        String url = new StringBuilder(baseUrl)
                .append("/")
                .append(langs)
                .append("/")
                .append(word)
                .toString();
        Document document;
        try {
            document = Jsoup.connect(url).get();
            return document.title();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
