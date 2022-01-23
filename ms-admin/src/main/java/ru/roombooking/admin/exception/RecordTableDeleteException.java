package ru.roombooking.admin.exception;

public class DeleteRecordTableException extends RuntimeException {
    public DeleteRecordTableException() {
        super();
    }

    public DeleteRecordTableException(String message) {
        super(message);
    }

    public DeleteRecordTableException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteRecordTableException(Throwable cause) {
        super(cause);
    }

    protected DeleteRecordTableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
