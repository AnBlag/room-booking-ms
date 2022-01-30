package ru.roombooking.employee.exception;

import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class DepartmentRequestException extends IllegalArgumentException {
    public DepartmentRequestException() {
        super();
    }

    public DepartmentRequestException(String s) {
        super(s);
    }

    public DepartmentRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentRequestException(Throwable cause) {
        super(cause);
    }
}