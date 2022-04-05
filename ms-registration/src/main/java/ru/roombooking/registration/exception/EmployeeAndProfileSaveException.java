package ru.roombooking.registration.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class EmployeeAndProfileSaveException extends RuntimeException {
    public EmployeeAndProfileSaveException() {
        super();
    }

    public EmployeeAndProfileSaveException(String message) {
        super(message);
    }

    public EmployeeAndProfileSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeAndProfileSaveException(Throwable cause) {
        super(cause);
    }

    protected EmployeeAndProfileSaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}