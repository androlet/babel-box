package com.learning.babelbox.features.mocks;

import com.learning.babelbox.domain.Signification;
import com.learning.babelbox.repository.SignificationRepository;
import org.springframework.stereotype.Component;

@Component
public class SignificationRepositoryMock extends BaseRepositoryMock<Signification> implements SignificationRepository {
}
