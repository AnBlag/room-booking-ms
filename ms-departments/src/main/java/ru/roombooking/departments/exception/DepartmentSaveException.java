package ru.roombooking.departments.exception;

public class DepartmentSaveException extends RuntimeException {
    public DepartmentSaveException() {
        super();
    }

    public DepartmentSaveException(String message) {
        super(message);
    }

    public DepartmentSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentSaveException(Throwable cause) {
        super(cause);
    }

    protected DepartmentSaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}