package ru.roombooking.front.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class RecordTableAndVscRoomRequestException extends IllegalArgumentException {
    public RecordTableAndVscRoomRequestException() {
    }

    public RecordTableAndVscRoomRequestException(String s) {
        super(s);
    }

    public RecordTableAndVscRoomRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordTableAndVscRoomRequestException(Throwable cause) {
        super(cause);
    }
}