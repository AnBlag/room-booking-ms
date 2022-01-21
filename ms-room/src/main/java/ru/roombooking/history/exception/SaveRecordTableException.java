package ru.roombooking.history.exception;

public class SaveRecordTableException extends RuntimeException{
    public SaveRecordTableException() {
        super();
    }

    public SaveRecordTableException(String message) {
        super(message);
    }

    public SaveRecordTableException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaveRecordTableException(Throwable cause) {
        super(cause);
    }

    protected SaveRecordTableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
