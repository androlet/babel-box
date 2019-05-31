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
        assertThat(searchResult.getFrom()).isEqualTo("en");
        assertThat(searchResult.getTo()).isEqualTo("fr");
        assertThat(searchResult.getOriginalTerm()).isEqualTo("over");
        assertThat(searchResult.getOriginalTermPronunciation()).isEqualTo("ˈəʊvə");

        ConnectorSearchResult.Signification current = searchResult.getResultList().get(0).getSignifications().get(0);
        assertThat(current.getDescription()).isEqualTo("au-dessus de");
        assertThat(current.getOriginalLanguageExample()).isEqualTo("They hung a picture over the fireplace.");
        assertThat(current.getResultLanguageExample()).isEqualTo("Ils ont accroché un tableau au-dessus de la cheminée.");

        current = searchResult.getResultList().get(3).getSignifications().get(0);
        assertThat(current.getDescription()).isEqualTo("sur");
        assertThat(current.getOriginalLanguageExample()).isEqualTo("They put sheets over the furniture to protect it.");
        assertThat(current.getResultLanguageExample()).isEqualTo("Ils ont posé les draps sur les meubles pour les protéger.");
        current = searchResult.getResultList().get(3).getSignifications().get(1);
        assertThat(current.getDescription()).isEqualTo("par-dessus");
        assertThat(current.getOriginalLanguageExample()).isEqualTo("They put sheets over the furniture to protect it.");
        assertThat(current.getResultLanguageExample()).isEqualTo("Ils ont posé les draps par-dessus les meubles pour les protéger.");

        current = searchResult.getResultList().get(8).getSignifications().get(0);
        assertThat(current.getDescription()).isEqualTo("restant");
        assertThat(current.getOriginalLanguageExample()).isEqualTo("If there is any food over after the party, you can take it.");
        assertThat(current.getResultLanguageExample()).isEqualTo("S'il reste de la nourriture après la fête, tu peux la prendre.");
        current = searchResult.getResultList().get(8).getSignifications().get(1);
        assertThat(current.getDescription()).isEqualTo("rester");
        assertThat(current.getOriginalLanguageExample()).isEqualTo(null);
        assertThat(current.getResultLanguageExample()).isEqualTo(null);

        assertThat(searchResult.getResultList().size()).isEqualTo(29);
    }
}
