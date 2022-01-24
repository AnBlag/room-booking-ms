package ru.roombooking.admin.exception;

public class UsersUpdateException extends RuntimeException {
    public UsersUpdateException() {
        super();
    }

    public UsersUpdateException(String message) {
        super(message);
    }

    public UsersUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsersUpdateException(Throwable cause) {
        super(cause);
    }

    protected UsersUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}