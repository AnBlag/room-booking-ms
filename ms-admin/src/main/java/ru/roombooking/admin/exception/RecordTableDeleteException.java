package ru.roombooking.admin.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class RecordTableDeleteException extends RuntimeException {
    public RecordTableDeleteException() {
        super();
    }

    public RecordTableDeleteException(String message) {
        super(message);
    }

    public RecordTableDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordTableDeleteException(Throwable cause) {
        super(cause);
    }

    protected RecordTableDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}