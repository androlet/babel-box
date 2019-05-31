package com.learning.babelbox.features;

import com.learning.babelbox.connectors.TranslationConnector;
import com.learning.babelbox.domain.Language;
import com.learning.babelbox.features.mocks.LanguageRepositoryMock;
import com.learning.babelbox.features.mocks.TranslationRepositoryMock;
import com.learning.babelbox.features.mocks.WordReferenceConnectorMock;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.learning.babelbox.*"})
public class CommonTest {

    public Language en;
    public Language fr;

    protected ApplicationContext applicationContext;

    protected TranslationController translationController;
    protected LanguageController languageController;

    protected TranslationRepositoryMock translationRepositoryMock;
    protected LanguageRepositoryMock languageRepositoryMock;

    protected WordReferenceConnectorMock wordReferenceConnectorMock;

    protected void initContext() {
        applicationContext = new AnnotationConfigApplicationContext(CommonTest.class);
        translationController = applicationContext.getBean(TranslationController.class);
        languageController = applicationContext.getBean(LanguageController.class);
        languageRepositoryMock = applicationContext.getBean(LanguageRepositoryMock.class);
        translationRepositoryMock = applicationContext.getBean(TranslationRepositoryMock.class);
        wordReferenceConnectorMock = (WordReferenceConnectorMock) applicationContext.getBean(TranslationConnector.class);

        en = new Language("en");
        fr = new Language("fr");
        languageRepositoryMock.save(en);
        languageRepositoryMock.save(fr);
    }
}
