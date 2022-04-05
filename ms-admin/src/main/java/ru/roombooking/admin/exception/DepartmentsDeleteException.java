package ru.roombooking.admin.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class DepartmentsDeleteException extends RuntimeException {
    public DepartmentsDeleteException() {
        super();
    }

    public DepartmentsDeleteException(String message) {
        super(message);
    }

    public DepartmentsDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentsDeleteException(Throwable cause) {
        super(cause);
    }

    protected DepartmentsDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}