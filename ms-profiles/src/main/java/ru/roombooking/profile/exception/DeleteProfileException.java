package ru.roombooking.profile.exception;

public class DeleteProfileException extends RuntimeException{
    public DeleteProfileException() {
        super();
    }

    public DeleteProfileException(String message) {
        super(message);
    }

    public DeleteProfileException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteProfileException(Throwable cause) {
        super(cause);
    }

    protected DeleteProfileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
