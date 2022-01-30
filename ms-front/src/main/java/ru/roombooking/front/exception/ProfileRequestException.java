package ru.roombooking.front.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class ProfileRequestException extends IllegalArgumentException {
    public ProfileRequestException() {
        super();
    }

    public ProfileRequestException(String s) {
        super(s);
    }

    public ProfileRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProfileRequestException(Throwable cause) {
        super(cause);
    }
}