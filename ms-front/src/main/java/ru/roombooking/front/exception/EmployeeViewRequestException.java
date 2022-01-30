package ru.roombooking.front.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class EmployeeViewRequestException extends IllegalArgumentException {
    public EmployeeViewRequestException() {
        super();
    }

    public EmployeeViewRequestException(String s) {
        super(s);
    }

    public EmployeeViewRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeViewRequestException(Throwable cause) {
        super(cause);
    }
}