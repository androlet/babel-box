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
    public void test() throws IOException {

        //Given
        Language source = new Language("en");
        Language target = new Language("fr");
        htmlParserMock.loadFile("over_wordreference.en_fr.html");

        //When
        ConnectorSearchResult searchResult = wordReferenceConnector.fetch(source, target, "over");

        //Then
        assertThat(searchResult.getOriginalTerm()).isEqualTo("over");
        assertThat(searchResult.getOriginalTermPronunciation()).isEqualTo("ˈəʊvə");
        assertThat(searchResult.getResultList().get(0)).isEqualTo("au-dessus de ");
        assertThat(searchResult.getResultList().size()).isEqualTo(10);
    }
}
