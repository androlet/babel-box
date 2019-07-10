package com.learning.babelbox.services;

import com.learning.babelbox.domain.Signification;
import com.learning.babelbox.repository.SignificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignificationService {

    private final SignificationRepository significationRepository;

    public SignificationService(SignificationRepository significationRepository) {
        this.significationRepository = significationRepository;
    }

    @Transactional
    public Signification create(Signification signification) {
        return significationRepository.save(signification);
    }
}
