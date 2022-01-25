package ru.roombooking.employee.exception;

public class EmployeeViewUpdateException extends RuntimeException{
    public EmployeeViewUpdateException() {
        super();
    }

    public EmployeeViewUpdateException(String message) {
        super(message);
    }

    public EmployeeViewUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeViewUpdateException(Throwable cause) {
        super(cause);
    }

    protected EmployeeViewUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}