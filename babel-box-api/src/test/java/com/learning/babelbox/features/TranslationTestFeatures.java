package com.learning.babelbox.features;

import com.learning.babelbox.connectors.dto.ConnectorSearchResult;
import com.learning.babelbox.domain.Translation;
import com.learning.babelbox.domain.TranslationKnowledge;
import com.learning.babelbox.exceptions.ReversedLanguageException;
import com.learning.babelbox.exceptions.WrongLanguageException;
import com.learning.babelbox.features.builders.ConnectorSearchResultBuilder;
import com.learning.babelbox.features.builders.TranslationBuilder;
import com.learning.babelbox.features.dto.QcmExercise;
import com.learning.babelbox.features.dto.TranslationResults;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class TranslationTestFeatures extends BaseFeaturesTest {

    @Before
    public void setUp() {
        initContext();
    }

    @Test
    public void should_respond_translations_WHEN_user_have_already_research_the_word() {
        //Given
        String searchedTerm = "hello";
        List<String> expectedTranslations = asList("salut", "bonjour");
        translationKnowledgeRepositoryMock.saveAll(
                TranslationBuilder.buildFrom(en, fr, searchedTerm, expectedTranslations).stream()
                .map(translation -> new TranslationKnowledge(translation, connectedUser))
                .collect(toList())
        );

        //When
        TranslationResults results = translationController.getTranslations(searchedTerm);

        //Then
        assertThat(results.getResults().get(0).getSignification()).isEqualTo(expectedTranslations.get(0));
        assertThat(results.getResults().get(1).getSignification()).isEqualTo(expectedTranslations.get(1));
    }

    @Test
    public void should_save_data_AND_respond_translations_WHEN_research_already_occured_with_another_user() {
        //Given
        String searchedTerm = "hello";
        List<String> expectedTranslations = asList("salut", "bonjour");
        translationRepositoryMock.saveAll(TranslationBuilder.buildFrom(en, fr, searchedTerm, expectedTranslations));

        //When
        TranslationResults results = translationController.getTranslations(searchedTerm);

        //Then
        List<TranslationKnowledge> storedData = translationKnowledgeRepositoryMock.findAll();
        assertThat(storedData.size()).isEqualTo(2);
        assertThat(storedData.get(0).getUser()).isEqualTo(connectedUser);
        assertThat(storedData.get(0).getTranslation()).isEqualTo(expectedTranslations.get(0));
        assertThat(storedData.get(0).getTranslation()).isEqualTo(expectedTranslations.get(1));

        assertThat(results.getResults().get(0).getSignification()).isEqualTo(expectedTranslations.get(0));
        assertThat(results.getResults().get(1).getSignification()).isEqualTo(expectedTranslations.get(1));
    }

    @Test
    public void should_save_data_AND_respond_translations_WHEN_nobody_has_researched_the_word_yet() {
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
        List<TranslationKnowledge> storedData = translationKnowledgeRepositoryMock.findAll();
        assertThat(storedData.size()).isEqualTo(4);
        assertThat(storedData.get(0).getUser()).isEqualTo(connectedUser);

        assertThat(results.getResults().get(0).getSignification()).isEqualTo("salut");
        assertThat(results.getResults().get(1).getSignification()).isEqualTo("salutation");
        assertThat(storedData.get(1).getTranslation().getOriginalExample()).isEqualTo("An example with hello !");
        assertThat(storedData.get(1).getTranslation().getTranslatedExample()).isEqualTo("Un exemple avec salutation !");
        assertThat(results.getResults().get(2).getSignification()).isEqualTo("bonjour");
        assertThat(results.getResults().get(3).getSignification()).isEqualTo("salutation");
        assertThat(storedData.get(3).getTranslation().getTranslatedExample()).isEqualTo(null);
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

    @Test
    public void should_get_random_qcm_exercise() {
        //given
        List<Translation> translationsStored = new ArrayList<>();
        translationsStored.addAll(TranslationBuilder.buildFrom(en, fr, "screw", asList("visser", "baiser", "hélice")));
        translationsStored.addAll(TranslationBuilder.buildFrom(en, fr, "test", asList("interrogation", "test", "analyse")));
        translationsStored.addAll(TranslationBuilder.buildFrom(en, fr, "mean", asList("signifier", "vouloir dire", "être sincère")));
        translationsStored.addAll(TranslationBuilder.buildFrom(en, fr, "poor", asList("pauvre", "médiocre", "modeste")));
        translationsStored = translationRepositoryMock.saveAll(translationsStored);

        List<TranslationKnowledge> translationKnowledgeList = translationsStored.stream()
                .map(translation -> new TranslationKnowledge(translation, connectedUser))
                .collect(toList());
        translationKnowledgeRepositoryMock.saveAll(translationKnowledgeList);

        int randomPositionRightAnswer = 2;
        randomProviderMock.setNextRandomNumber(randomPositionRightAnswer);


        //When
        QcmExercise qcm = translationController.getQcm();

        //Then
        assertThat(qcm.getOptions()).hasSize(4);
        String qcmQuestion = qcm.getQcmQuestion();
        List<QcmExercise.QcmOption> rightOptions = qcm.getOptions().stream()
                .filter(option -> option.getTranslation().getOriginalTerm().equals(qcmQuestion))
                .collect(toList());
        assertThat(rightOptions).hasSize(1);
        QcmExercise.QcmOption rightOption = qcm.getOptions().get(randomPositionRightAnswer);
        assertThat(rightOption.isRightAnswer()).isTrue();
        Set<String> optionsContents = qcm.getOptions().stream().map(QcmExercise.QcmOption::getContent).collect(Collectors.toSet());
        assertThat(optionsContents).hasSize(4);
    }
}
