package ru.roombooking.front.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(BAD_REQUEST)
public class VscRoomBadRequestException extends IllegalArgumentException {
    public VscRoomBadRequestException() {
        super();
    }

    public VscRoomBadRequestException(String s) {
        super(s);
    }

    public VscRoomBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public VscRoomBadRequestException(Throwable cause) {
        super(cause);
    }
}