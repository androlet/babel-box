package com.learning.babelbox.exceptions;

import org.springframework.http.HttpStatus;

public class WrongLanguageException extends BabelBoxWebException {
    public WrongLanguageException() {
        super();
        status = HttpStatus.NOT_FOUND;
        errorMessage = "None translations have been found.";
    }
}
