package ru.roombooking.front.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(BAD_REQUEST)
public class SaveEmployeeBadRequestException extends IllegalArgumentException {
    public SaveEmployeeBadRequestException() {
        super();
    }

    public SaveEmployeeBadRequestException(String s) {
        super(s);
    }

    public SaveEmployeeBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaveEmployeeBadRequestException(Throwable cause) {
        super(cause);
    }
}