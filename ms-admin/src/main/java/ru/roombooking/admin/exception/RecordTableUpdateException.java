package ru.roombooking.admin.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class RecordTableUpdateException extends RuntimeException {
    public RecordTableUpdateException() {
        super();
    }

    public RecordTableUpdateException(String message) {
        super(message);
    }

    public RecordTableUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordTableUpdateException(Throwable cause) {
        super(cause);
    }

    protected RecordTableUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}