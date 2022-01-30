package ru.roombooking.history.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class SecurityRequestException extends IllegalArgumentException {
    public SecurityRequestException() {
        super();
    }

    public SecurityRequestException(String s) {
        super(s);
    }

    public SecurityRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecurityRequestException(Throwable cause) {
        super(cause);
    }
}