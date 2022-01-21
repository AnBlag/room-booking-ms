package ru.roombooking.admin.exception;

public class DeleteDepartmentsException extends RuntimeException {
    public DeleteDepartmentsException() {
        super();
    }

    public DeleteDepartmentsException(String message) {
        super(message);
    }

    public DeleteDepartmentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteDepartmentsException(Throwable cause) {
        super(cause);
    }

    protected DeleteDepartmentsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
