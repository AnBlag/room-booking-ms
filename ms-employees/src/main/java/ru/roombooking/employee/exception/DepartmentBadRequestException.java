package ru.roombooking.employee.exception;

import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(BAD_REQUEST)
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