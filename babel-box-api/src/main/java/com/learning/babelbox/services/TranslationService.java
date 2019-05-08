package com.learning.babelbox.services;

import com.learning.babelbox.connectors.TranslationConnector;
import com.learning.babelbox.domain.Language;
import com.learning.babelbox.domain.Translation;
import com.learning.babelbox.domain.Word;
import com.learning.babelbox.features.dto.TranslationResults;
import com.learning.babelbox.repository.TranslationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TranslationService {

    private final TranslationRepository translationRepository;
    private final LanguageService languageService;
    private final WordService wordService;
    private final TranslationConnector translationConnector;

    public TranslationResults getTranslation(String searchedTerm) {
        String result = translationConnector.fetch(searchedTerm);
        return new TranslationResults(translationRepository.findByOriginalTerm(searchedTerm));
    }

    @Transactional
    public void generates() {
        createEnglishToFrenchTranslations("overhead", "en haut", "au-dessus de nos têtes");
        createEnglishToFrenchTranslations("trainer", "entraîneur, entraîneuse", "dresseur, dresseuse", "formateur, formatrice");
    }

    private void createEnglishToFrenchTranslations(String originalTerm, String... translations) {
        Language english = languageService.getLanguage("en");
        Language french = languageService.getLanguage("fr");
        Word originalWord = wordService.create(english, originalTerm);
        for(String translation : translations) {
            Word translatedTerm = wordService.create(french, translation);
            translationRepository.save(new Translation(originalWord, translatedTerm));
        }
    }

    public List<Translation> getAllTranslations() {
        return translationRepository.findAll();
    }
}
