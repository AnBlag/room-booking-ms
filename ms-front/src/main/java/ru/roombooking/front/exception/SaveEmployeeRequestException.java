package ru.roombooking.front.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class SaveEmployeeRequestException extends IllegalArgumentException {
    public SaveEmployeeRequestException() {
        super();
    }

    public SaveEmployeeRequestException(String s) {
        super(s);
    }

    public SaveEmployeeRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaveEmployeeRequestException(Throwable cause) {
        super(cause);
    }
}