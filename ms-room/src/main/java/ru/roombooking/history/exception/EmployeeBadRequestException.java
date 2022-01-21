package ru.roombooking.history.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeBadRequestException extends IllegalArgumentException {
    public EmployeeBadRequestException() {
        super();
    }

    public EmployeeBadRequestException(String s) {
        super(s);
    }

    public EmployeeBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeBadRequestException(Throwable cause) {
        super(cause);
    }
}
