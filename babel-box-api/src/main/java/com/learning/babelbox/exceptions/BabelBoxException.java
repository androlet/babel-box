package com.learning.babelbox.exceptions;

public abstract class BabelBoxException extends RuntimeException {
    public BabelBoxException() {
    }

    public BabelBoxException(String message) {
        super(message);
    }

    public BabelBoxException(String message, Throwable cause) {
        super(message, cause);
    }

    public BabelBoxException(Throwable cause) {
        super(cause);
    }

    public BabelBoxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
