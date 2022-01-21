package ru.roombooking.front.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RecordTableAndVscRoomBadRequestException extends IllegalArgumentException {
    public RecordTableAndVscRoomBadRequestException() {
    }

    public RecordTableAndVscRoomBadRequestException(String s) {
        super(s);
    }

    public RecordTableAndVscRoomBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordTableAndVscRoomBadRequestException(Throwable cause) {
        super(cause);
    }
}
