package ru.roombooking.history.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.history.model.dto.RecordTableDTO;
import ru.roombooking.history.service.impl.HistoryNotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PropertySource("classpath:record-text.properties")
@RequestMapping("/history")
public class HistoryController {
    private final HistoryNotificationService historyNotificationService;

    @GetMapping("/")
    public ResponseEntity<List<RecordTableDTO>> findAll() {
        return ResponseEntity.ok(historyNotificationService.findAll());
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<RecordTableDTO> deleteHistoryRecordById(@PathVariable String id) {
        //return ResponseEntity.ok(recordTableNotificationService.deleteRecordById(id));
        return null;
    }
}