package com.learning.babelbox.features;

import com.learning.babelbox.domain.Language;
import com.learning.babelbox.features.dto.LanguageOption;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class LanguageTest extends CommonTest {

    @Before
    public void setUp() {
        initContext();
    }

    @Test
    public void should_get_all_languages() {
        //Given
        languageRepositoryMock.save(new Language("es"));

        //When
        List<LanguageOption> languageList = languageController.getAllLanguages();

        //Then
        Assertions.assertThat(languageList.size()).isEqualTo(3);
    }
}
