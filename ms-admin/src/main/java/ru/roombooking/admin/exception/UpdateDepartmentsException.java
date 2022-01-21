package ru.roombooking.admin.exception;

public class UpdateDepartmentsException extends RuntimeException {
    public UpdateDepartmentsException() {
        super();
    }

    public UpdateDepartmentsException(String message) {
        super(message);
    }

    public UpdateDepartmentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateDepartmentsException(Throwable cause) {
        super(cause);
    }

    protected UpdateDepartmentsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
