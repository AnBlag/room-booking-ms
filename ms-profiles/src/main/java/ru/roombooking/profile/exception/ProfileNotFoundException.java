package ru.roombooking.profile.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(NOT_FOUND)
public class ProfileNotFoundException extends IllegalArgumentException {
    public ProfileNotFoundException() {
        super();
    }

    public ProfileNotFoundException(String s) {
        super(s);
    }

    public ProfileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProfileNotFoundException(Throwable cause) {
        super(cause);
    }
}