package ru.roombooking.employee.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ResponseStatus(BAD_REQUEST)
public class EmployeeSaveException extends RuntimeException{
    public EmployeeSaveException() {
        super();
    }

    public EmployeeSaveException(String message) {
        super(message);
    }

    public EmployeeSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeSaveException(Throwable cause) {
        super(cause);
    }

    protected EmployeeSaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}