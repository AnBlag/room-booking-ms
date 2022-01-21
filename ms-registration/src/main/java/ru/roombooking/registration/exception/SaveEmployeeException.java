package ru.roombooking.registration.exception;

public class SaveEmployeeException extends RuntimeException{
    public SaveEmployeeException() {
        super();
    }

    public SaveEmployeeException(String message) {
        super(message);
    }

    public SaveEmployeeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaveEmployeeException(Throwable cause) {
        super(cause);
    }

    protected SaveEmployeeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
