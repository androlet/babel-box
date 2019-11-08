package com.learning.babelbox.repository;

import com.learning.babelbox.domain.Language;
import com.learning.babelbox.domain.TranslationKnowledge;
import com.learning.babelbox.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslationKnowledgeRepository extends JpaRepository<TranslationKnowledge, Long> {

    @Query(
        "select tk from TranslationKnowledge tk " +
        "join Translation t on tk.translation = t.id " +
        "join Word w on t.originalTerm = w.id " +
        "join Signification s on t.signification = s.id " +
        "where w.language = :source " +
        "and s.language = :target " +
        "and tk.user = :user " +
        "and w.spelling = :originalTerm "
    )
    List<TranslationKnowledge> findByUserAndOriginalTerm(
            @Param("source") Language source,
            @Param("target") Language target,
            @Param("user") User user,
            @Param("originalTerm") String originalTerm
    );

    @Query(
        "select tk from TranslationKnowledge tk " +
        "join Translation t on tk.translation = t.id " +
        "join Word w on t.originalTerm = w.id " +
        "join Signification s on t.signification = s.id " +
        "where w.language = :source " +
        "and s.language = :target " +
        "and tk.user = :user " +
        "and tk.id IN ( " +
            "select min(tk1.id) from TranslationKnowledge tk1 " +
            "join Translation t1 on tk1.translation = t1.id " +
            "where t.originalTerm = t1.originalTerm " +
            "and tk1.numberOfRemaining = ( " +
                "select min(tk2.numberOfRemaining) from TranslationKnowledge tk2 " +
                "join Translation t2 on tk2.translation = t2.id " +
                "where t1.originalTerm = t2.originalTerm " +
            ") " +
        ") " +
        "order by tk.numberOfRemaining "
    )
    List<TranslationKnowledge> findMostLessRemainedTranslationKnowledges(
            @Param("source") Language source,
            @Param("target") Language target,
            @Param("user") User user,
            Pageable pageable
    );
}
