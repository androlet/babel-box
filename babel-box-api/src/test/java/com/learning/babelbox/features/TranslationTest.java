package com.learning.babelbox.features;

import com.learning.babelbox.builders.TranslationBuilder;
import com.learning.babelbox.connectors.dto.ConnectorSearchResult;
import com.learning.babelbox.features.dto.TranslationResults;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class TranslationTest extends CommonTest {

    @Before
    public void setUp() {
        initContext();
    }

    @Test
    public void should_get_translations_WHEN_translations_have_already_been_registered() {
        //Given
        String searchedTerm = "hello";
        List<String> expectedTranslations = asList("salut", "bonjour");
        translationRepositoryMock.saveAll(TranslationBuilder.buildFrom(en, fr, searchedTerm, new ArrayList<>()));

        //When
        TranslationResults results = translationController.getTranslations(searchedTerm);

        //Then
        assertThat(results.getSignifications()).isEqualTo(expectedTranslations);
    }

    @Test
    public void should_registered_and_get_translations_WHEN_translations_have_NOT_already_been_registered() {
        //Given
        String searchedTerm = "hello";
        String pronunciation = "pronunciation";
        List<String> expectedTranslations = asList("salut", "bonjour");
        ConnectorSearchResult searchResult = new ConnectorSearchResult(searchedTerm, pronunciation, new ArrayList<>());
        wordReferenceConnectorMock.setResult(searchedTerm, searchResult);

        //When
        TranslationResults results = translationController.getTranslations(searchedTerm);

        //Then
        assertThat(results.getSignifications()).isEqualTo(expectedTranslations);
        assertThat(translationRepositoryMock.findAll().size()).isEqualTo(2);
    }
}
