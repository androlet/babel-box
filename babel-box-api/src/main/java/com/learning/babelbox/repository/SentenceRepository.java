package com.learning.babelbox.repository;

import com.learning.babelbox.domain.Language;
import com.learning.babelbox.domain.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SentenceRepository extends JpaRepository<Sentence, Long> {

    Optional<Sentence> findByLanguageAndContent(Language language, String content);
}
