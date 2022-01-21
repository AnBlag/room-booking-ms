package ru.roombooking.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.admin.model.RecordTableView;
import ru.roombooking.admin.model.dto.RecordTableRequest;
import ru.roombooking.admin.model.dto.RecordTableViewListAndVscRoomListRequest;
import ru.roombooking.admin.service.notification.RecordTableNotificationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/records")
public class RecordTableAdminController {
    private final RecordTableNotificationService recordTableNotificationService;

    @GetMapping("/")
    public ResponseEntity<RecordTableViewListAndVscRoomListRequest> records(@RequestParam(required = false) String search) {
        return ResponseEntity.ok(recordTableNotificationService.records(search));
    }

    @PostMapping("/")
    public ResponseEntity<RecordTableViewListAndVscRoomListRequest> findRecords(@RequestBody RecordTableView findRecord) {
        return ResponseEntity.ok(recordTableNotificationService.findRecords(findRecord));
    }

    @PutMapping("/save")
    public void updateRecords(@RequestBody RecordTableRequest recordTableRequest) {
        recordTableNotificationService.updateRecords(recordTableRequest);
    }

    /*@GetMapping("/delete/{id}")
    public String askDeleteRecord(@PathVariable String id, ModelMap modelMap) {
        modelMap.addAttribute("recordTableId", id);
        return "deleterecord";
    }*/

    @DeleteMapping("/delete/{id}")
    public void deleteRecord(@PathVariable String id) {
        recordTableNotificationService.deleteRecord(id);
    }
}
