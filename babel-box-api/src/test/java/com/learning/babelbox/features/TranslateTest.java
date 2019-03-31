package com.learning.babelbox.features;

import com.learning.babelbox.CommonTest;
import com.learning.babelbox.domain.Translation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.sql.DataSource;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class TranslateTest extends CommonTest {

    @Autowired
    TranslationController translationController;

    @MockBean
    DataSource dataSource = null;

    @Test
    public void shouldReturnTranslations() {
        //Given
        String searchedTerm = "hello";
        List<String> expectedTranslations = asList("salut", "bonjour");
        //When
        translationController.getTranslation(searchedTerm);
        //Then
        //assertThat(result.getTranslationProposals()).isEqualTo(expectedTranslations);
    }
}
