package ru.roombooking.history.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.history.model.dto.RecordTableDTO;
import ru.roombooking.history.service.impl.RecordTableNotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/record")
public class RecordController {
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
}