package com.learning.babelbox.features;

import com.learning.babelbox.exceptions.BabelBoxWebException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Error> handleException(Exception ex) {
        System.err.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Error("The server has encounter an unexpected error."));
    }

    @ExceptionHandler(BabelBoxWebException.class)
    public final ResponseEntity<Error> handleException(BabelBoxWebException ex) {
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
