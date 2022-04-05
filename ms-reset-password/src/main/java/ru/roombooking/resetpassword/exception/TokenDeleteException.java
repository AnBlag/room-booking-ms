package ru.roombooking.resetpassword.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(value = BAD_REQUEST, reason="Ошибка удаления токена")
public class TokenDeleteException extends RuntimeException {
    public TokenDeleteException() {
        super();
    }

    public TokenDeleteException(String message) {
        super(message);
    }

    public TokenDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenDeleteException(Throwable cause) {
        super(cause);
    }

    protected TokenDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}