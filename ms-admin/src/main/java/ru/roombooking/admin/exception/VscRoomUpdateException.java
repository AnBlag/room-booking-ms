package ru.roombooking.admin.exception;

public class VscRoomUpdateException extends RuntimeException {
    public VscRoomUpdateException() {
        super();
    }

    public VscRoomUpdateException(String message) {
        super(message);
    }

    public VscRoomUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public VscRoomUpdateException(Throwable cause) {
        super(cause);
    }

    protected VscRoomUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}