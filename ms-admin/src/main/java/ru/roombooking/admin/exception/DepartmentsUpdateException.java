package ru.roombooking.admin.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class DepartmentsUpdateException extends RuntimeException {
    public DepartmentsUpdateException() {
        super();
    }

    public DepartmentsUpdateException(String message) {
        super(message);
    }

    public DepartmentsUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentsUpdateException(Throwable cause) {
        super(cause);
    }

    protected DepartmentsUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}