package ru.roombooking.departments.exception;

public class DepartmentDeleteException extends RuntimeException {
    public DepartmentDeleteException() {
        super();
    }

    public DepartmentDeleteException(String message) {
        super(message);
    }

    public DepartmentDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentDeleteException(Throwable cause) {
        super(cause);
    }

    protected DepartmentDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}