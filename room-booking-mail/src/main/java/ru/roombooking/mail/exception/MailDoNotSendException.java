package ru.roombooking.mail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Ошибка отправки уведомления")
public class MailDoNotSendException extends RuntimeException {
    public MailDoNotSendException() {
        super();
    }

    public MailDoNotSendException(String message) {
        super(message);
    }

    public MailDoNotSendException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailDoNotSendException(Throwable cause) {
        super(cause);
    }

    protected MailDoNotSendException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
