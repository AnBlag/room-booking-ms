package ru.roombooking.departments.exception;

public class UpdateDepartmentException extends RuntimeException{
    public UpdateDepartmentException() {
        super();
    }

    public UpdateDepartmentException(String message) {
        super(message);
    }

    public UpdateDepartmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateDepartmentException(Throwable cause) {
        super(cause);
    }

    protected UpdateDepartmentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
