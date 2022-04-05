package ru.roombooking.front.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class RecordTableRequestException extends IllegalArgumentException {
    public RecordTableRequestException() {
    }

    public RecordTableRequestException(String s) {
        super(s);
    }

    public RecordTableRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordTableRequestException(Throwable cause) {
        super(cause);
    }
}