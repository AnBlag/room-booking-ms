package ru.roombooking.history.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SecurityBadRequestException extends IllegalArgumentException {
    public SecurityBadRequestException() {
        super();
    }

    public SecurityBadRequestException(String s) {
        super(s);
    }

    public SecurityBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecurityBadRequestException(Throwable cause) {
        super(cause);
    }
}
