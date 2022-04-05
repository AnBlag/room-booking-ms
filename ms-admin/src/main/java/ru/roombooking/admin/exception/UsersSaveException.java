package ru.roombooking.admin.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class UsersSaveException extends RuntimeException {
    public UsersSaveException() {
        super();
    }

    public UsersSaveException(String message) {
        super(message);
    }

    public UsersSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsersSaveException(Throwable cause) {
        super(cause);
    }

    protected UsersSaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}