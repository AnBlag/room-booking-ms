package ru.roombooking.admin.exception;

import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class VscRoomRequestException extends IllegalArgumentException {
    public VscRoomRequestException() {
        super();
    }

    public VscRoomRequestException(String s) {
        super(s);
    }

    public VscRoomRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public VscRoomRequestException(Throwable cause) {
        super(cause);
    }
}