package com.learning.babelbox.repository;

import com.learning.babelbox.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
}
