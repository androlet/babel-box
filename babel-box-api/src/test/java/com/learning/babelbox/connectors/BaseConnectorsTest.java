package com.learning.babelbox.connectors;

import com.learning.babelbox.platform.HtmlParserMock;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.learning.babelbox.connectors", "com.learning.babelbox.platform"})
public class BaseConnectorsTest {

    protected ApplicationContext applicationContext;
    protected WordReferenceConnector wordReferenceConnector;
    protected HtmlParserMock htmlParserMock;

    protected void initContext() {
        applicationContext = new AnnotationConfigApplicationContext(BaseConnectorsTest.class);
        wordReferenceConnector = applicationContext.getBean(WordReferenceConnector.class);
        htmlParserMock = applicationContext.getBean(HtmlParserMock.class);
    }
}