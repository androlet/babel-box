package com.learning.babelbox.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthenticatedUserException extends BabelBoxWebException {
    public UnauthenticatedUserException() {
        super();
        status = HttpStatus.UNAUTHORIZED;
        errorMessage = "Missing/Invalid token.";
    }
}
