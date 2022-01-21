package ru.roombooking.admin.exception;

public class UpdateUsersException extends RuntimeException {
    public UpdateUsersException() {
        super();
    }

    public UpdateUsersException(String message) {
        super(message);
    }

    public UpdateUsersException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateUsersException(Throwable cause) {
        super(cause);
    }

    protected UpdateUsersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
