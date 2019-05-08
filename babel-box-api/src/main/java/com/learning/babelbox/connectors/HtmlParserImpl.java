package com.learning.babelbox.connectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HtmlParserImpl implements HtmlParser {

    @Override
    public Document download(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
