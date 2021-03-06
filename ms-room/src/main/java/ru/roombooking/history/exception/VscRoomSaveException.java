package ru.roombooking.history.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ResponseStatus(BAD_REQUEST)
public class VscRoomSaveException extends RuntimeException {
    public VscRoomSaveException() {
        super();
    }

    public VscRoomSaveException(String message) {
        super(message);
    }

    public VscRoomSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public VscRoomSaveException(Throwable cause) {
        super(cause);
    }

    protected VscRoomSaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}