package ru.roombooking.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.admin.model.dto.RecordTableDTO;
import ru.roombooking.admin.service.notification.HistoryNotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/history")
public class HistoryAdminController {
    private final HistoryNotificationService historyNotificationService;

    @GetMapping("/")
    public ResponseEntity<List<RecordTableDTO>> records() {
        return ResponseEntity.ok(historyNotificationService.records());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRecord(@PathVariable String id) {

    }
}