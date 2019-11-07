package com.learning.babelbox.exceptions;

import org.springframework.http.HttpStatus;

public class UnavailableExerciseException extends BabelBoxWebException {
    public UnavailableExerciseException(String message) {
        super();
        status = HttpStatus.NOT_FOUND;
        errorMessage = message;
    }

    public UnavailableExerciseException() {
        this("Unavailable Exercise, it miss some settings.");
    }
}
