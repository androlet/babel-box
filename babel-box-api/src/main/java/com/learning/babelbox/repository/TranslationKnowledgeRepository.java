package com.learning.babelbox.repository;

import com.learning.babelbox.domain.TranslationKnowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationKnowledgeRepository extends JpaRepository<TranslationKnowledge, Long> {

}
