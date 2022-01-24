package ru.roombooking.admin.exception;

public class DepartmentsSaveException extends RuntimeException {
    public DepartmentsSaveException() {
        super();
    }

    public DepartmentsSaveException(String message) {
        super(message);
    }

    public DepartmentsSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentsSaveException(Throwable cause) {
        super(cause);
    }

    protected DepartmentsSaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}