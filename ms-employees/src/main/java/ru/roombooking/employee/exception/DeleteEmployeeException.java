package ru.roombooking.employee.exception;

public class DeleteEmployeeException extends RuntimeException{
    public DeleteEmployeeException() {
        super();
    }

    public DeleteEmployeeException(String message) {
        super(message);
    }

    public DeleteEmployeeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteEmployeeException(Throwable cause) {
        super(cause);
    }

    protected DeleteEmployeeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
