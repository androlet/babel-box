package com.learning.babelbox.connectors;

import com.learning.babelbox.connectors.mocks.HtmlParserMock;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.learning.babelbox.connectors"})
public class ConnectorsCommonTest {

    protected ApplicationContext applicationContext;
    protected WordReferenceConnector wordReferenceConnector;
    protected HtmlParserMock htmlParserMock;

    protected void initContext() {
        applicationContext = new AnnotationConfigApplicationContext(ConnectorsCommonTest.class);
        wordReferenceConnector = applicationContext.getBean(WordReferenceConnector.class);
        htmlParserMock = applicationContext.getBean(HtmlParserMock.class);
    }
}