package com.learning.babelbox.features.mocks;

import com.learning.babelbox.domain.Example;
import com.learning.babelbox.repository.ExampleRepository;
import org.springframework.stereotype.Component;

@Component
public class ExampleRepositoryMock extends BaseRepositoryMock<Example> implements ExampleRepository {
}
