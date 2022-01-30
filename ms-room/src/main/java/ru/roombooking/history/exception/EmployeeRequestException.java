package ru.roombooking.history.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class EmployeeRequestException extends IllegalArgumentException {
    public EmployeeRequestException() {
        super();
    }

    public EmployeeRequestException(String s) {
        super(s);
    }

    public EmployeeRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeRequestException(Throwable cause) {
        super(cause);
    }
}