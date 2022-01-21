package ru.roombooking.employee.exception;

public class UpdateEmployeeViewException extends RuntimeException{
    public UpdateEmployeeViewException() {
        super();
    }

    public UpdateEmployeeViewException(String message) {
        super(message);
    }

    public UpdateEmployeeViewException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateEmployeeViewException(Throwable cause) {
        super(cause);
    }

    protected UpdateEmployeeViewException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
