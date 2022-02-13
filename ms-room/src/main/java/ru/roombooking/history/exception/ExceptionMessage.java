package ru.roombooking.history.exception;

public enum ExceptionMessage {
    RECORD_NOT_FOUND("Не найдена запись"),
    THIS_TIME_IS_BUSY("Данное время занято"),
    ID_ROOM_NOT_FOUND("Не найден id комнаты"),
    ID_NOT_FOUND("Не найден ID"),
    NO_RECORD_ACCESS("Нет доступа к записи!"),
    NUMBER_ROOM_ID_NOT_FOUND("Не найден NumberRoomID");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
