package com.learning.babelbox.features;

import com.learning.babelbox.connectors.TranslationConnector;
import com.learning.babelbox.domain.Language;
import com.learning.babelbox.domain.User;
import com.learning.babelbox.features.mocks.LanguageRepositoryMock;
import com.learning.babelbox.features.mocks.TranslationKnowledgeRepositoryMock;
import com.learning.babelbox.features.mocks.TranslationRepositoryMock;
import com.learning.babelbox.features.mocks.WordReferenceConnectorMock;
import com.learning.babelbox.mocks.BaseRepositoryMock;
import com.learning.babelbox.platform.RandomProviderMock;
import com.learning.babelbox.security.AuthToken;
import com.learning.babelbox.mocks.UserRepositoryMock;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.learning.babelbox.security.builders.UserBuilder.buildActiveUserFrom;

@Configuration
@ComponentScan(
        value = {
                "com.learning.babelbox.connectors",
                "com.learning.babelbox.domain",
                "com.learning.babelbox.exceptions",
                "com.learning.babelbox.features",
                "com.learning.babelbox.platform",
                "com.learning.babelbox.repository",
                "com.learning.babelbox.services",
                "com.learning.babelbox.mocks"
        }
)
public class BaseFeaturesTest {

    public User connectedUser;
    public Language en;
    public Language fr;

    protected ApplicationContext applicationContext;

    protected TranslationController translationController;
    protected LanguageController languageController;

    protected TranslationKnowledgeRepositoryMock translationKnowledgeRepositoryMock;
    protected TranslationRepositoryMock translationRepositoryMock;
    protected LanguageRepositoryMock languageRepositoryMock;
    protected UserRepositoryMock userRepositoryMock;

    protected RandomProviderMock randomProviderMock;

    protected WordReferenceConnectorMock wordReferenceConnectorMock;

    protected void initContext() {
        applicationContext = new AnnotationConfigApplicationContext(BaseFeaturesTest.class);
        applicationContext.getBeansOfType(BaseRepositoryMock.class)
                .values().stream().forEach(BaseRepositoryMock::reset);

        randomProviderMock = applicationContext.getBean(RandomProviderMock.class);

        translationController = applicationContext.getBean(TranslationController.class);
        languageController = applicationContext.getBean(LanguageController.class);

        userRepositoryMock = applicationContext.getBean(UserRepositoryMock.class);
        languageRepositoryMock = applicationContext.getBean(LanguageRepositoryMock.class);
        translationRepositoryMock = applicationContext.getBean(TranslationRepositoryMock.class);
        translationKnowledgeRepositoryMock = applicationContext.getBean(TranslationKnowledgeRepositoryMock.class);

        wordReferenceConnectorMock = (WordReferenceConnectorMock) applicationContext.getBean(TranslationConnector.class);

        en = new Language("en");
        fr = new Language("fr");
        languageRepositoryMock.save(en);
        languageRepositoryMock.save(fr);

        connectedUser = userRepositoryMock.save(buildActiveUserFrom("email", "password", "token"));
        SecurityContextHolder.getContext().setAuthentication(new AuthToken("token", connectedUser));
    }
}
