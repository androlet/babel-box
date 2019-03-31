package com.learning.babelbox.repository;

import com.learning.babelbox.domain.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    Language findByLocaleCode(String localeCode);
}
