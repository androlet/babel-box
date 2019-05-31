package com.learning.babelbox.exceptions;

import org.springframework.http.HttpStatus;

public class ReversedLanguageException extends BabelBoxWebException {
    public ReversedLanguageException() {
        super();
        status = HttpStatus.NOT_FOUND;
        errorMessage = "None translations have been found. But you should get some results, if you exchange languages.";
    }
}
