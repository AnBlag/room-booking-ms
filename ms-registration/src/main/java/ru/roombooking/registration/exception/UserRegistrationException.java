package ru.roombooking.registration.exception;

public class UserRegistrationException extends RuntimeException {
    public UserRegistrationException() {
        super();
    }

    public UserRegistrationException(String message) {
        super(message);
    }

    public UserRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRegistrationException(Throwable cause) {
        super(cause);
    }

    protected UserRegistrationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}