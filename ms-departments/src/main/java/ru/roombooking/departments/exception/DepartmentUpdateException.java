package ru.roombooking.departments.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ResponseStatus(BAD_REQUEST)
public class DepartmentUpdateException extends RuntimeException {
    public DepartmentUpdateException() {
        super();
    }

    public DepartmentUpdateException(String message) {
        super(message);
    }

    public DepartmentUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentUpdateException(Throwable cause) {
        super(cause);
    }

    protected DepartmentUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}