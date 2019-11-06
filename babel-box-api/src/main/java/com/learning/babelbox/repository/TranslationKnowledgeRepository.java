package com.learning.babelbox.repository;

import com.learning.babelbox.domain.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long> {

    @Query("select t from Translation t join Word w on t.originalTerm = w " +
            "where w.spelling = :originalTerm")
    List<Translation> findByOriginalTerm(@Param("originalTerm") String originalTerm);
}
