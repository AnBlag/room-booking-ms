package ru.roombooking.history.exception;

public class UpdateVscRoomException extends RuntimeException{
    public UpdateVscRoomException() {
        super();
    }

    public UpdateVscRoomException(String message) {
        super(message);
    }

    public UpdateVscRoomException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateVscRoomException(Throwable cause) {
        super(cause);
    }

    protected UpdateVscRoomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
