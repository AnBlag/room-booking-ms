package ru.roombooking.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.admin.model.dto.VscRoomDTO;
import ru.roombooking.admin.model.dto.VscRoomRequest;
import ru.roombooking.admin.service.notification.VscRoomNotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/vscrooms")
public class VscRoomAdminController {
    private final VscRoomNotificationService vscRoomNotificationService;

    @GetMapping("/")
    public ResponseEntity<List<VscRoomDTO>> vscRooms() {
        return ResponseEntity.ok(vscRoomNotificationService.vscRooms());
    }

    @PostMapping("/save")
    public void updateVscRoom(@RequestBody VscRoomRequest vscRoomRequest) {
        vscRoomNotificationService.updateVscRoom(vscRoomRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRoom(@PathVariable String id) {
        vscRoomNotificationService.deleteRoom(id);
    }

    @PostMapping("/addroom")
    public void saveNewRoom(@RequestBody VscRoomDTO vscRoom) {
        vscRoomNotificationService.saveNewRoom(vscRoom);
    }
}