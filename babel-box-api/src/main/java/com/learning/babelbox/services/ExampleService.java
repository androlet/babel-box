package com.learning.babelbox.services;

import com.learning.babelbox.domain.Example;
import com.learning.babelbox.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final ExampleRepository exampleRepository;

    public Example create(Example example) {
        return exampleRepository.save(example);
    }
}
