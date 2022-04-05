package ru.roombooking.departments.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ResponseStatus(value = BAD_REQUEST, reason = "Не найден ID")
public class DepartmentBadRequestException extends IllegalArgumentException {
    public DepartmentBadRequestException() {
        super();
    }

    public DepartmentBadRequestException(String s) {
        super(s);
    }

    public DepartmentBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentBadRequestException(Throwable cause) {
        super(cause);
    }
}