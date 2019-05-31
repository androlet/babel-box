package com.learning.babelbox.features;

import com.learning.babelbox.exceptions.BabelBoxWebException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(BabelBoxWebException.class)
    public final ResponseEntity<Error> handleException(BabelBoxWebException ex, WebRequest request) {
        return ResponseEntity.status(ex.getStatus()).body(new Error(ex.getErrorMessage()));
    }

    private static class Error {
        private String message;

        public Error(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
