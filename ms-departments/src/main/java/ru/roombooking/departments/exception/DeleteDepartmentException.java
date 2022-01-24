package ru.roombooking.departments.exception;

public class DeleteDepartmentException extends RuntimeException {
    public DeleteDepartmentException() {
        super();
    }

    public DeleteDepartmentException(String message) {
        super(message);
    }

    public DeleteDepartmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteDepartmentException(Throwable cause) {
        super(cause);
    }

    protected DeleteDepartmentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}