package ru.roombooking.history.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.history.model.RecordTable;
import ru.roombooking.history.model.dto.RecordTableDTO;
import ru.roombooking.history.service.impl.RecordTableNotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PropertySource("classpath:record-text.properties")
@RequestMapping("/record")
public class RecordController {
    /*@Value("${record.url}")
    private String recordUrl;*/

    private final RecordTableNotificationService recordTableNotificationService;

    @GetMapping("/")
    public ResponseEntity<List<RecordTableDTO>> findAll() {
        return ResponseEntity.ok(recordTableNotificationService.findAll());
    }

    @GetMapping("/{index}")
    public ResponseEntity<List<RecordTableDTO>> findByIndex(@PathVariable String index) {
        return ResponseEntity.ok(recordTableNotificationService.findByIndex(index));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<RecordTableDTO>> findAllByEmployeeFullNameAndRecordAndIsActiveAndNumberRoom() {
        return ResponseEntity.ok(recordTableNotificationService.findAllByEmployeeFullNameAndRecordAndIsActiveAndNumberRoom());
    }

    @PostMapping("/save/{login}")
    public ResponseEntity<RecordTableDTO> saveRecord(@RequestBody RecordTableDTO recordTableDTO,
                                                                    @PathVariable String login) {
        return ResponseEntity.ok(recordTableNotificationService.saveRecord(recordTableDTO, login));
    }

    @PutMapping("/update/{login}")
    public ResponseEntity<RecordTableDTO> updateRecord(@RequestBody RecordTableDTO recordTableDTO,
                                                                      @PathVariable String login) {
        return ResponseEntity.ok(recordTableNotificationService.updateRecord(recordTableDTO, login));
    }

    @DeleteMapping("/delete/{login}")
    public ResponseEntity<RecordTableDTO> deleteRecord(@RequestBody RecordTableDTO recordTableDTO,
                                                                      @PathVariable String login) {
        return ResponseEntity.ok(recordTableNotificationService.deleteRecord(recordTableDTO, login));
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<RecordTableDTO> deleteRecordById(@PathVariable String id) {
        return ResponseEntity.ok(recordTableNotificationService.deleteRecordById(id));
    }

    @PutMapping("/batch-update-records")
    public void batchUpdateRecords(@RequestBody List<RecordTableDTO> recordTableList) {
        recordTableNotificationService.batchUpdateRecords(recordTableList);
    }


    /*private void sendConfirmMessageToEmployee(RecordTableDTO recordTableDTO, String roomId) {
        recordTableDTO.setRoomId(roomId);
        String subject = "Бронирование комнаты №" + recordTableDTO.getRoomId();
        String message = getMessageForSaveRecord(recordTableDTO);
        mailSenderService.send(recordTableDTO.getEmail(), subject, message);
    }*/

    /*private String getMessageForSaveRecord(RecordTableDTO recordTableDTO) {
        return "Вы забронировали комнату №" + recordTableDTO.getRoomId() + "\n"
                + "Тема: " + recordTableDTO.getTitle() + "\n"
                + "Дата бронирования: " + recordTableDTO.getStart().toLocalDate() + "\n"
                + "Время бронирования: с " + recordTableDTO.getStart().toLocalTime()
                + " по " + recordTableDTO.getEnd().toLocalTime() + "\n"
                + "Подробнее: " + recordUrl + recordTableDTO.getRoomId();
    }*/





    /*private void sendConfirmUpdateMessageToEmployee(RecordTableDTO previousRecordTableDTO, RecordTableDTO recordTableDTO) {
        setCurrentZone(recordTableDTO);
        String subject = "Изменение в бронирование комнаты №" + recordTableDTO.getRoomId();
        String message = getMessageForUpdateRecord(previousRecordTableDTO, recordTableDTO);
        mailSenderService.send(recordTableDTO.getEmail(), subject, message);
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

    private void sendConfirmDeleteMessageToEmployee(RecordTableDTO recordTableDTO) {
        recordTableDTO.setRoomId(vscRoomService.findById(recordTableDTO.getNumberRoomId()).getNumberRoom().toString());
        String subject = "Отмена бронирования комнаты №" + recordTableDTO.getRoomId();
        mailSenderService.send(recordTableDTO.getEmail(), subject, getMessageForDeleteRecord(recordTableDTO));
    }

    private String getMessageForDeleteRecord(RecordTableDTO recordTableDTO) {
        return "Отменено бронирование комнаты №" + recordTableDTO.getRoomId() + "\n"
                + "Тема: " + recordTableDTO.getTitle() + "\n"
                + "Дата бронирования: " + recordTableDTO.getStart().toLocalDate() + "\n"
                + "Время бронирования: с " + recordTableDTO.getStart().toLocalTime()
                + " по " + recordTableDTO.getEnd().toLocalTime() + "\n"
                + "Подробнее: " + recordUrl  + recordTableDTO.getRoomId();
    }*/


}
