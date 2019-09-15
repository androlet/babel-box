package com.learning.babelbox.features;

import com.learning.babelbox.connectors.dto.ConnectorSearchResult;
import com.learning.babelbox.domain.Translation;
import com.learning.babelbox.exceptions.ReversedLanguageException;
import com.learning.babelbox.exceptions.WrongLanguageException;
import com.learning.babelbox.features.builders.ConnectorSearchResultBuilder;
import com.learning.babelbox.features.builders.TranslationBuilder;
import com.learning.babelbox.features.dto.TranslationResults;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class TranslationTestFeatures extends BaseFeaturesTest {

    @Before
    public void setUp() {
        initContext();
    }

    @Test
    public void should_get_translations_WHEN_translations_have_already_been_registered() {
        //Given
        String searchedTerm = "hello";
        List<String> expectedTranslations = asList("salut", "bonjour");
        translationRepositoryMock.saveAll(TranslationBuilder.buildFrom(en, fr, searchedTerm, expectedTranslations));

        //When
        TranslationResults results = translationController.getTranslations(searchedTerm);

        //Then
        assertThat(results.getResults().get(0).getSignification()).isEqualTo(expectedTranslations.get(0));
        assertThat(results.getResults().get(1).getSignification()).isEqualTo(expectedTranslations.get(1));
    }

    @Test
    public void should_registered_and_get_translations_WHEN_translations_have_NOT_already_been_registered() {
        //Given
        String searchedTerm = "hello";
        String pronunciation = "pronunciation";
        ConnectorSearchResult.Signification signification1 = ConnectorSearchResultBuilder.significationFrom(
                "salut",
                "An example with hello !",
                "Un exemple avec salut !");
        ConnectorSearchResult.Signification signification2 = ConnectorSearchResultBuilder.significationFrom(
                "salutation",
                "An example with hello !",
                "Un exemple avec salutation !");
        ConnectorSearchResult.Signification signification3 = ConnectorSearchResultBuilder.significationFrom(
                "bonjour",
                "An example with hello !",
                "Un exemple avec bonjour !");
        ConnectorSearchResult.Signification signification4 = ConnectorSearchResultBuilder.significationFrom(
                "salutation",
                null,
                null);

        List<ConnectorSearchResult.Result> searchResults = asList(
            new ConnectorSearchResult.Result(asList(signification1, signification2)),
            new ConnectorSearchResult.Result(asList(signification3, signification4))
        );
        ConnectorSearchResult searchResult = new ConnectorSearchResult("en", "fr", searchedTerm, pronunciation, searchResults);
        wordReferenceConnectorMock.setResult(searchedTerm, searchResult);

        //When
        TranslationResults results = translationController.getTranslations(searchedTerm);

        //Then
        List<Translation> storedData = translationRepositoryMock.findAll();
        assertThat(storedData.size()).isEqualTo(4);
        assertThat(results.getResults().get(0).getSignification()).isEqualTo("salut");
        assertThat(results.getResults().get(1).getSignification()).isEqualTo("salutation");
        assertThat(storedData.get(1).getOriginalExample()).isEqualTo("An example with hello !");
        assertThat(storedData.get(1).getTranslatedExample()).isEqualTo("Un exemple avec salutation !");
        assertThat(results.getResults().get(2).getSignification()).isEqualTo("bonjour");
        assertThat(results.getResults().get(3).getSignification()).isEqualTo("salutation");
        assertThat(storedData.get(3).getTranslatedExample()).isEqualTo(null);
    }

    @Test(expected = ReversedLanguageException.class)
    public void should_send_a_ReversedLanguageException_when_connector_swapped_languages() {

        //Given
        String searchedTerm = "hello";
        ConnectorSearchResult searchResult = new ConnectorSearchResult("fr", "en", "bonjour", null, null);
        wordReferenceConnectorMock.setResult(searchedTerm, searchResult);

        //When
        translationController.getTranslations(searchedTerm);
    }

    @Test(expected = WrongLanguageException.class)
    public void should_send_a_WrongLanguageException_when_connector_swapped_languages() {

        //Given
        String searchedTerm = "hello";
        ConnectorSearchResult searchResult = new ConnectorSearchResult("fr", "de", "bonjour", null, null);
        wordReferenceConnectorMock.setResult(searchedTerm, searchResult);

        //When
        translationController.getTranslations(searchedTerm);
    }
}