package ru.roombooking.history.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Не найдена запись")
public class RecordTableAndEmployeeException extends RuntimeException {
    public RecordTableAndEmployeeException() {
    }

    public RecordTableAndEmployeeException(String message) {
        super(message);
    }

    public RecordTableAndEmployeeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordTableAndEmployeeException(Throwable cause) {
        super(cause);
    }

    public RecordTableAndEmployeeException(
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
