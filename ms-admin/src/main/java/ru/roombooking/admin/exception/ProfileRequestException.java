package ru.roombooking.admin.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class ProfileRequestException extends RuntimeException {
    public ProfileRequestException() {
        super();
    }

    public ProfileRequestException(String message) {
        super(message);
    }

    public ProfileRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProfileRequestException(Throwable cause) {
        super(cause);
    }

    protected ProfileRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}