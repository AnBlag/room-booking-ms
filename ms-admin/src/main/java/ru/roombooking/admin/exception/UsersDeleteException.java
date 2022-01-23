package ru.roombooking.admin.exception;

public class DeleteUsersException extends RuntimeException {
    public DeleteUsersException() {
        super();
    }

    public DeleteUsersException(String message) {
        super(message);
    }

    public DeleteUsersException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteUsersException(Throwable cause) {
        super(cause);
    }

    protected DeleteUsersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
