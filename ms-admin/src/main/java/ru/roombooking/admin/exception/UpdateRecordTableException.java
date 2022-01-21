package ru.roombooking.admin.exception;

public class UpdateRecordTableException extends RuntimeException {
    public UpdateRecordTableException() {
        super();
    }

    public UpdateRecordTableException(String message) {
        super(message);
    }

    public UpdateRecordTableException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateRecordTableException(Throwable cause) {
        super(cause);
    }

    protected UpdateRecordTableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
