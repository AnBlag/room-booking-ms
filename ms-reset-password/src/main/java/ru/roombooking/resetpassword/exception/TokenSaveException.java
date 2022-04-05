package ru.roombooking.resetpassword.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(value = BAD_REQUEST, reason="Ошибка сохранения токена")
public class TokenSaveException extends RuntimeException {
    public TokenSaveException() {
        super();
    }

    public TokenSaveException(String message) {
        super(message);
    }

    public TokenSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenSaveException(Throwable cause) {
        super(cause);
    }

    protected TokenSaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}