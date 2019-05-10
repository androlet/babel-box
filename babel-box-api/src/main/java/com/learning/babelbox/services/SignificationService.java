package com.learning.babelbox.services;

import com.learning.babelbox.domain.Signification;
import com.learning.babelbox.repository.SignificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignificationService {

    private final SignificationRepository significationRepository;

    public Signification create(Signification signification) {
        return significationRepository.save(signification);
    }
}
