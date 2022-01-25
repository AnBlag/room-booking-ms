package ru.roombooking.front.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(BAD_REQUEST)
public class RecordTableBadRequestException extends IllegalArgumentException {
    public RecordTableBadRequestException() {
    }

    public RecordTableBadRequestException(String s) {
        super(s);
    }

    public RecordTableBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordTableBadRequestException(Throwable cause) {
        super(cause);
    }
}