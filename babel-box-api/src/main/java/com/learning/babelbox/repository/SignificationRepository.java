package com.learning.babelbox.repository;

import com.learning.babelbox.domain.Signification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignificationRepository extends JpaRepository<Signification, Long> {
}
