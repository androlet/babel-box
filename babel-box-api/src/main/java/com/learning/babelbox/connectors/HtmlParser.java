package com.learning.babelbox.connectors;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface HtmlParser {
    Document download(String url) throws IOException;
}
