package ru.roombooking.registration.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class ProfileSaveException extends RuntimeException {
    public ProfileSaveException() {
        super();
    }

    public ProfileSaveException(String message) {
        super(message);
    }

    public ProfileSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProfileSaveException(Throwable cause) {
        super(cause);
    }

    protected ProfileSaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}