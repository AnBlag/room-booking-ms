package ru.roombooking.front.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(NOT_FOUND)
public class EmployeeNotFoundException extends IllegalArgumentException {
    public EmployeeNotFoundException() {
        super();
    }

    public EmployeeNotFoundException(String s) {
        super(s);
    }

    public EmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeNotFoundException(Throwable cause) {
        super(cause);
    }
}