package ru.roombooking.admin.exception;

public class UsersDeleteException extends RuntimeException {
    public UsersDeleteException() {
        super();
    }

    public UsersDeleteException(String message) {
        super(message);
    }

    public UsersDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsersDeleteException(Throwable cause) {
        super(cause);
    }

    protected UsersDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}