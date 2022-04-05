package ru.roombooking.history.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class RecordTableSaveException extends RuntimeException {
    public RecordTableSaveException() {
        super();
    }

    public RecordTableSaveException(String message) {
        super(message);
    }

    public RecordTableSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordTableSaveException(Throwable cause) {
        super(cause);
    }

    protected RecordTableSaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}