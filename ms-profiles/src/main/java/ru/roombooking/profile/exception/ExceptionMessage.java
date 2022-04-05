package ru.roombooking.profile.exception;

public enum ExceptionMessage {
    ID_NOT_FOUND("Не найден ID"),
    PROFILE_NOT_FOUND("Профиль не найден");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
