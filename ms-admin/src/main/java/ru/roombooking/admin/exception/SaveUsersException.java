package ru.roombooking.admin.exception;

public class SaveUsersException extends RuntimeException {
    public SaveUsersException() {
        super();
    }

    public SaveUsersException(String message) {
        super(message);
    }

    public SaveUsersException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaveUsersException(Throwable cause) {
        super(cause);
    }

    protected SaveUsersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
