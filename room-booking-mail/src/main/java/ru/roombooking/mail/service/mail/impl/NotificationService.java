package ru.roombooking.mail.service.mail.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.roombooking.mail.exception.MailDoNotSendException;
import ru.roombooking.mail.model.dto.MailRequest;
import ru.roombooking.mail.model.dto.RecordTableDTO;
import ru.roombooking.mail.service.mail.MailSenderService;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:record-text.properties")
public class NotificationService {
    @Value("${record.url}")
    private String recordUrl;
    private final MailSenderService mailSenderService;

    public void sendConfirmMessageToEmployee(RecordTableDTO recordTableDTO, String roomId) {
        try {
            recordTableDTO.setRoomId(roomId);
            setCurrentZone(recordTableDTO);
            String subject = "Бронирование комнаты №" + recordTableDTO.getRoomId();
            String message = getMessageForSaveRecord(recordTableDTO);
            mailSenderService.send(recordTableDTO.getEmail(), subject, message);
        } catch (Exception e) {
            throw new MailDoNotSendException();
        }
    }

    private String getMessageForSaveRecord(RecordTableDTO recordTableDTO) {
        return "Вы забронировали комнату №" + recordTableDTO.getRoomId() + "\n"
                + "Тема: " + recordTableDTO.getTitle() + "\n"
                + "Дата бронирования: " + recordTableDTO.getStart().toLocalDate() + "\n"
                + "Время бронирования: с " + recordTableDTO.getStart().toLocalTime()
                + " по " + recordTableDTO.getEnd().toLocalTime() + "\n"
                + "Подробнее: " + recordUrl + recordTableDTO.getRoomId();
    }

    public void sendConfirmUpdateMessageToEmployee(RecordTableDTO previousRecordTableDTO, RecordTableDTO recordTableDTO) {
        try {
            setCurrentZone(recordTableDTO);
            setCurrentZone(previousRecordTableDTO);
            String subject = "Изменение в бронирование комнаты №" + recordTableDTO.getRoomId();
            String message = getMessageForUpdateRecord(previousRecordTableDTO, recordTableDTO);
            mailSenderService.send(recordTableDTO.getEmail(), subject, message);
        } catch (Exception e) {
            throw new MailDoNotSendException();
        }
    }

    private String getMessageForUpdateRecord(RecordTableDTO previousRecordTableDTO, RecordTableDTO recordTableDTO) {
        return "Изменение в бронировании комнаты №" + recordTableDTO.getRoomId() + "\n"
                + "Тема: " + recordTableDTO.getTitle() + "\n"
                + "Старое время: " + "\n"
                + "Дата бронирования: " + previousRecordTableDTO.getStart().toLocalDate() + "\n"
                + "Время бронирования: с " + previousRecordTableDTO.getStart().toLocalTime()
                + " по " + previousRecordTableDTO.getEnd().toLocalTime() + "\n"
                + "Новое время: " + "\n"
                + "Дата бронирования: " + recordTableDTO.getStart().toLocalDate() + "\n"
                + "Время бронирования: с " + recordTableDTO.getStart().toLocalTime()
                + " по " + recordTableDTO.getEnd().toLocalTime() + "\n"
                + "Подробнее: " + recordUrl  + recordTableDTO.getRoomId();
    }

    public void sendConfirmDeleteMessageToEmployee(RecordTableDTO recordTableDTO) {
        try {
            setCurrentZone(recordTableDTO);
            String subject = "Отмена бронирования комнаты №" + recordTableDTO.getRoomId();
            mailSenderService.send(recordTableDTO.getEmail(), subject, getMessageForDeleteRecord(recordTableDTO));
        } catch (Exception e) {
            throw new MailDoNotSendException();
        }

    }

    private String getMessageForDeleteRecord(RecordTableDTO recordTableDTO) {
        return "Отменено бронирование комнаты №" + recordTableDTO.getRoomId() + "\n"
                + "Тема: " + recordTableDTO.getTitle() + "\n"
                + "Дата бронирования: " + recordTableDTO.getStart().toLocalDate() + "\n"
                + "Время бронирования: с " + recordTableDTO.getStart().toLocalTime()
                + " по " + recordTableDTO.getEnd().toLocalTime() + "\n"
                + "Подробнее: " + recordUrl  + recordTableDTO.getRoomId();
    }


    private void setCurrentZone(RecordTableDTO recordTableDTO) {
        recordTableDTO.setStart(recordTableDTO.getStart().withZoneSameInstant(recordTableDTO.getTimeZone()));
        recordTableDTO.setEnd(recordTableDTO.getEnd().withZoneSameInstant(recordTableDTO.getTimeZone()));
    }


    public void send (MailRequest mailRequest) {
        try {
            mailSenderService.send(mailRequest.getEmailTo(), mailRequest.getSubject(), mailRequest.getMessage());
        } catch (Exception e) {
            throw new MailDoNotSendException();
        }
    }
}
