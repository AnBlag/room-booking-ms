package ru.roombooking.resetpassword.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Ошибка установки нового пароля")
public class SetNewPasswordException extends RuntimeException {
    public SetNewPasswordException() {
        super();
    }

    public SetNewPasswordException(String message) {
        super(message);
    }

    public SetNewPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public SetNewPasswordException(Throwable cause) {
        super(cause);
    }

    protected SetNewPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
