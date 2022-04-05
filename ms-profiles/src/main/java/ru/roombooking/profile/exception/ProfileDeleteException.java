package ru.roombooking.profile.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ResponseStatus(BAD_REQUEST)
public class ProfileDeleteException extends RuntimeException {
    public ProfileDeleteException() {
        super();
    }

    public ProfileDeleteException(String message) {
        super(message);
    }

    public ProfileDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProfileDeleteException(Throwable cause) {
        super(cause);
    }

    protected ProfileDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}