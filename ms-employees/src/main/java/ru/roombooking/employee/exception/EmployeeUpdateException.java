package ru.roombooking.employee.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ResponseStatus(BAD_REQUEST)
public class EmployeeUpdateException extends RuntimeException{
    public EmployeeUpdateException() {
        super();
    }

    public EmployeeUpdateException(String message) {
        super(message);
    }

    public EmployeeUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeUpdateException(Throwable cause) {
        super(cause);
    }

    protected EmployeeUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}