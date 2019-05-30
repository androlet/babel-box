package com.learning.babelbox.repository;

import com.learning.babelbox.domain.Sentence;
import com.learning.babelbox.domain.SentenceTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SentenceTranslationRepository extends JpaRepository<SentenceTranslation, Long> {

    Optional<SentenceTranslation> findByOriginalSentenceAndTargetSentence(Sentence original, Sentence target);
}
