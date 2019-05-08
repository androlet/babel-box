package com.learning.babelbox.features;

import com.learning.babelbox.CommonTest;
import com.learning.babelbox.builders.TranslationBuilder;
import com.learning.babelbox.features.dto.TranslationResults;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class TranslationTest extends CommonTest {

    @Before
    public void setUp() {
        initContext();
    }

    @Test
    public void shouldReturnTranslations() {
        //Given
        String searchedTerm = "hello";
        List<String> expectedTranslations = asList("salut", "bonjour");
        translationRepositoryMock.saveAll(TranslationBuilder.buildFrom(searchedTerm, expectedTranslations));

        //When
        TranslationResults results = translationController.getTranslation(searchedTerm);

        //Then
        assertThat(results.getTranslationTerms()).isEqualTo(expectedTranslations);
    }
}
