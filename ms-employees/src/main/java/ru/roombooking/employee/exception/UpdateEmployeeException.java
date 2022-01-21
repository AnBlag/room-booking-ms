package ru.roombooking.employee.exception;

public class UpdateEmployeeException extends RuntimeException{
    public UpdateEmployeeException() {
        super();
    }

    public UpdateEmployeeException(String message) {
        super(message);
    }

    public UpdateEmployeeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateEmployeeException(Throwable cause) {
        super(cause);
    }

    protected UpdateEmployeeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
