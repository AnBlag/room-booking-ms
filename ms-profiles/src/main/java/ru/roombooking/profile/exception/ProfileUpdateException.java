package ru.roombooking.profile.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ResponseStatus(BAD_REQUEST)
public class ProfileUpdateException extends RuntimeException{
    public ProfileUpdateException() {
        super();
    }

    public ProfileUpdateException(String message) {
        super(message);
    }

    public ProfileUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProfileUpdateException(Throwable cause) {
        super(cause);
    }

    protected ProfileUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}