package com.learning.babelbox.repository;

import com.learning.babelbox.domain.Language;
import com.learning.babelbox.domain.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long> {

    @Query(
        "select t from Translation t " +
        "join Word w on t.originalTerm = w.id " +
        "join Signification s on t.signification = s.id " +
        "where w.language = :source " +
        "and s.language = :target " +
        "and w.spelling = :originalTerm "
    )
    List<Translation> findByOriginalTerm(
            @Param("source") Language source,
            @Param("target") Language target,
            @Param("originalTerm") String originalTerm
    );
}
