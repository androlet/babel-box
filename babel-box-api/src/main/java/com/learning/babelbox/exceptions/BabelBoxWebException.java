package com.learning.babelbox.exceptions;

import org.springframework.http.HttpStatus;

public class BabelBoxWebException extends BabelBoxException {
    protected HttpStatus status;
    protected String errorMessage;

    public HttpStatus getStatus() {
        return status;
    }

    public BabelBoxWebException with(HttpStatus status) {
        this.status = status;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
