package ru.roombooking.employee.exception;

public enum ExceptionMessages {
    ID_NOT_FOUND("Не найден ID"),
    EMPLOYEE_NOT_FOUND_BY_PROFILE_ID("Не найден employee с таким profileId");

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}