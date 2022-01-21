package ru.roombooking.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
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
