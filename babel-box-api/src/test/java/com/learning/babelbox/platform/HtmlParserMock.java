package com.learning.babelbox.platform;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@Primary
public class HtmlParserMock implements HtmlParser {

    private File file;

    public void loadFile(String path) throws IOException {
        file = new ClassPathResource(path).getFile();
    }

    @Override
    public Document download(String url) throws IOException {
        return Jsoup.parse(file, "UTF-8");
    }
}
