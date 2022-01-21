package ru.roombooking.history.exception;

public class DeleteVscRoomException extends RuntimeException{
    public DeleteVscRoomException() {
        super();
    }

    public DeleteVscRoomException(String message) {
        super(message);
    }

    public DeleteVscRoomException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteVscRoomException(Throwable cause) {
        super(cause);
    }

    protected DeleteVscRoomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
