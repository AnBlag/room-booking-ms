package ru.roombooking.resetpassword.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND, reason = "Не найден токен")
public class TokenNotFoundException extends IllegalArgumentException {
    public TokenNotFoundException() {
        super();
    }

    public TokenNotFoundException(String s) {
        super(s);
    }

    public TokenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenNotFoundException(Throwable cause) {
        super(cause);
    }
}
