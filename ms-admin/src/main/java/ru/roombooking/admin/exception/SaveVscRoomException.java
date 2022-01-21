package ru.roombooking.admin.exception;

public class SaveVscRoomException extends RuntimeException {
    public SaveVscRoomException() {
        super();
    }

    public SaveVscRoomException(String message) {
        super(message);
    }

    public SaveVscRoomException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaveVscRoomException(Throwable cause) {
        super(cause);
    }

    protected SaveVscRoomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
