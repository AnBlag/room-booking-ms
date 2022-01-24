package ru.roombooking.admin.exception;

public class VscRoomDeleteException extends RuntimeException {
    public VscRoomDeleteException() {
        super();
    }

    public VscRoomDeleteException(String message) {
        super(message);
    }

    public VscRoomDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public VscRoomDeleteException(Throwable cause) {
        super(cause);
    }

    protected VscRoomDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}