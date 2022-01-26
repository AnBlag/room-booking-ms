package ru.roombooking.history.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.history.model.RecordTableView;
import ru.roombooking.history.service.impl.RecordTableViewNotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/record-table-view")
public class RecordTableViewController {
    private final RecordTableViewNotificationService recordTableViewNotificationService;

    @GetMapping("/")
    public ResponseEntity<List<RecordTableView>> findAll() {
        return ResponseEntity.ok(recordTableViewNotificationService.findAll());
    }

    @GetMapping("/get-record-table-view-list-by-URL-params")
    public ResponseEntity<List<RecordTableView>> getRecordTableViewListByURLParams(@RequestParam String search) {
        return ResponseEntity.ok(recordTableViewNotificationService.getRecordTableViewListByURLParams(search));
    }

    @PostMapping("/get-record-table-view-list-by-record-table-view-params")
    public ResponseEntity<List<RecordTableView>> getRecordTableViewListByRecordTableViewParams(@RequestBody RecordTableView recordTableViewParams) {
        return ResponseEntity.ok(recordTableViewNotificationService.getRecordTableViewListByRecordTableViewParams(recordTableViewParams));
    }
}