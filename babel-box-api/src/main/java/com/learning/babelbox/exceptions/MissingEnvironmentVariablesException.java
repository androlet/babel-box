package com.learning.babelbox.exceptions;

public class MissingEnvironmentVariablesException extends BabelBoxException {
    public MissingEnvironmentVariablesException() {
        super("Missing ENV variables.");
    }
}
