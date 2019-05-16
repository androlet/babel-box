package com.learning.babelbox.services;

import com.learning.babelbox.domain.Example;
import com.learning.babelbox.repository.ExampleRepository;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    private final ExampleRepository exampleRepository;

    public ExampleService(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    public Example create(Example example) {
        return exampleRepository.save(example);
    }
}
