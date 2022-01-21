package ru.roombooking.registration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProfileBadRequestException extends IllegalArgumentException {
    public ProfileBadRequestException() {
        super();
    }

    public ProfileBadRequestException(String s) {
        super(s);
    }

    public ProfileBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProfileBadRequestException(Throwable cause) {
        super(cause);
    }
}
