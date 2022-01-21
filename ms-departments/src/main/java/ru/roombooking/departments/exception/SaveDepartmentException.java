package ru.roombooking.departments.exception;

public class SaveDepartmentException extends RuntimeException{
    public SaveDepartmentException() {
        super();
    }

    public SaveDepartmentException(String message) {
        super(message);
    }

    public SaveDepartmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaveDepartmentException(Throwable cause) {
        super(cause);
    }

    protected SaveDepartmentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
