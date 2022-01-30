package ru.roombooking.admin.exception;

import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class RecordTableViewRequestException extends IllegalArgumentException {
    public RecordTableViewRequestException() {
    }

    public RecordTableViewRequestException(String s) {
        super(s);
    }

    public RecordTableViewRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordTableViewRequestException(Throwable cause) {
        super(cause);
    }
}