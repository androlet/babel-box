package com.learning.babelbox.services;

import com.learning.babelbox.connectors.TranslationConnector;
import com.learning.babelbox.connectors.dto.ConnectorSearchResult;
import com.learning.babelbox.domain.Language;
import com.learning.babelbox.domain.Translation;
import com.learning.babelbox.domain.Word;
import com.learning.babelbox.features.dto.TranslationResults;
import com.learning.babelbox.repository.TranslationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TranslationService {

    private final TranslationRepository translationRepository;
    private final LanguageService languageService;
    private final WordService wordService;
    private final TranslationConnector translationConnector;

    @Transactional
    public TranslationResults getTranslationResults(Language source, Language target, String searchedTerm) {
        List<Translation> translations = translationRepository.findByOriginalTerm(searchedTerm);
        if(!translations.isEmpty()) {
            return new TranslationResults(translations);
        }
        return new TranslationResults(fetchAndSaveTranslations(source, target, searchedTerm));
    }

    @Transactional
    public List<Translation> fetchAndSaveTranslations(Language source, Language target, String searchedTerm) {
        ConnectorSearchResult searchResult = translationConnector.fetch(source, target, searchedTerm);
        Word originalTerm = wordService.create(
                new Word(source, searchResult.getOriginalTerm(), searchResult.getOriginalTermPronunciation())
        );
        return searchResult.getResultList().stream().map(result -> {
            Word translatedTerm = wordService.create(new Word(target, result));
            return translationRepository.save(new Translation(originalTerm, translatedTerm));
        }).collect(Collectors.toList());
    }

    @Transactional
    public void generates() {
        createEnglishToFrenchTranslations("overhead", "en haut", "au-dessus de nos têtes");
        createEnglishToFrenchTranslations("trainer", "entraîneur, entraîneuse", "dresseur, dresseuse", "formateur, formatrice");
    }

    private void createEnglishToFrenchTranslations(String originalTerm, String... translations) {
        Language english = languageService.getLanguage("en");
        Language french = languageService.getLanguage("fr");
        Word originalWord = wordService.create(new Word(english, originalTerm));
        for(String translation : translations) {
            Word translatedTerm = wordService.create(new Word(french, translation));
            translationRepository.save(new Translation(originalWord, translatedTerm));
        }
    }

    public List<Translation> getAllTranslations() {
        return translationRepository.findAll();
    }
}
