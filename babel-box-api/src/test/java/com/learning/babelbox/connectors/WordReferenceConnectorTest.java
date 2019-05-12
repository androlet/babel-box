package com.learning.babelbox.connectors;

import com.learning.babelbox.connectors.dto.ConnectorSearchResult;
import com.learning.babelbox.domain.Language;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class WordReferenceConnectorTest extends ConnectorsCommonTest {

    @Before
    public void setUp() {
        initContext();
    }

    @Test
    public void It_should_retrieve_all_desired_translations_of_wordreference_OVER_en_fr() throws IOException {

        //Given
        Language source = new Language("en");
        Language target = new Language("fr");
        htmlParserMock.loadFile("over_wordreference.en_fr.html");

        //When
        ConnectorSearchResult searchResult = wordReferenceConnector.fetch(source, target, "over");

        //Then
        assertThat(searchResult.getOriginalTerm()).isEqualTo("over");
        assertThat(searchResult.getOriginalTermPronunciation()).isEqualTo("ˈəʊvə");

        assertThat(searchResult.getResultList().get(0).getSignifications()).contains("au-dessus de");
        assertThat(searchResult.getResultList().get(0).getOriginalLanguageExamples()).contains("They hung a picture over the fireplace.");
        assertThat(searchResult.getResultList().get(0).getResultLanguageExamples()).contains("Ils ont accroché un tableau au-dessus de la cheminée.");

        assertThat(searchResult.getResultList().get(3).getSignifications()).contains("sur", "par-dessus");
        assertThat(searchResult.getResultList().get(3).getOriginalLanguageExamples()).contains("They put sheets over the furniture to protect it.");
        assertThat(searchResult.getResultList().get(3).getResultLanguageExamples()).contains(
                "Ils ont posé les draps par-dessus les meubles pour les protéger.",
                "Ils ont posé les draps par-dessus les meubles pour les protéger."
        );

        assertThat(searchResult.getResultList().get(8).getSignifications()).contains("restant", "rester");
        assertThat(searchResult.getResultList().get(8).getOriginalLanguageExamples()).contains("If there is any food over after the party, you can take it.");
        assertThat(searchResult.getResultList().get(8).getResultLanguageExamples()).containsOnly("S'il reste de la nourriture après la fête, tu peux la prendre.");

        assertThat(searchResult.getResultList().size()).isEqualTo(29);
    }
}
