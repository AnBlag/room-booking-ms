package ru.roombooking.profile.exception;

public class UpdateProfileException extends RuntimeException{
    public UpdateProfileException() {
        super();
    }

    public UpdateProfileException(String message) {
        super(message);
    }

    public UpdateProfileException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateProfileException(Throwable cause) {
        super(cause);
    }

    protected UpdateProfileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
