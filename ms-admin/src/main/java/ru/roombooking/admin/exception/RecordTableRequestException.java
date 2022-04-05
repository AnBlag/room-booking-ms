package ru.roombooking.admin.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class RecordTableRequestException extends RuntimeException {
    public RecordTableRequestException() {
        super();
    }

    public RecordTableRequestException(String message) {
        super(message);
    }

    public RecordTableRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordTableRequestException(Throwable cause) {
        super(cause);
    }

    protected RecordTableRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}