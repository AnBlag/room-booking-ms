package ru.roombooking.front.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(BAD_REQUEST)
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