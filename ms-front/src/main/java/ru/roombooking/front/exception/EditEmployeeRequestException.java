package ru.roombooking.front.exception;

import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class EditEmployeeRequestException extends IllegalArgumentException {
    public EditEmployeeRequestException() {
        super();
    }

    public EditEmployeeRequestException(String s) {
        super(s);
    }

    public EditEmployeeRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public EditEmployeeRequestException(Throwable cause) {
        super(cause);
    }
}