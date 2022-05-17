package com.example.castaticanalyzer.exceptions;

import lombok.Getter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
public class BadUserDataInputException extends BadCredentialsException {
    private List<FieldError> errors;

    public BadUserDataInputException(String msg) {
        super(msg);
    }

    public BadUserDataInputException() {
        super("Wrong email or/and password!");
    }

    public BadUserDataInputException(List<FieldError> errors) {
        super("Wrong email or/and password!");
        this.errors = errors;
    }

    public BadUserDataInputException(String message, List<FieldError> errors) {
        super(message);
        this.errors = errors;
    }
}
