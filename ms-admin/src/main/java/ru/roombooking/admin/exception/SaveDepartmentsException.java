package ru.roombooking.admin.exception;

public class SaveDepartmentsException extends RuntimeException {
    public SaveDepartmentsException() {
        super();
    }

    public SaveDepartmentsException(String message) {
        super(message);
    }

    public SaveDepartmentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaveDepartmentsException(Throwable cause) {
        super(cause);
    }

    protected SaveDepartmentsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
