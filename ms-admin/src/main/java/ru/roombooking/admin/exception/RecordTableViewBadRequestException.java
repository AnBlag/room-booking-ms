package ru.roombooking.admin.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RecordTableViewBadRequestException extends IllegalArgumentException {
    public RecordTableViewBadRequestException() {
    }

    public RecordTableViewBadRequestException(String s) {
        super(s);
    }

    public RecordTableViewBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordTableViewBadRequestException(Throwable cause) {
        super(cause);
    }
}
