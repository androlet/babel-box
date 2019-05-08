package com.learning.babelbox;

import com.learning.babelbox.features.TranslationController;
import com.learning.babelbox.mocks.TranslationRepositoryMock;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CommonTest {

    protected ApplicationContext applicationContext;

    protected TranslationController translationController;

    protected TranslationRepositoryMock translationRepositoryMock;

    protected void initContext() {
        applicationContext = new AnnotationConfigApplicationContext(ApplicationTestContext.class);
        translationController = applicationContext.getBean(TranslationController.class);
        translationRepositoryMock = applicationContext.getBean(TranslationRepositoryMock.class);
    }
}
