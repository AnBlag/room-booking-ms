package ru.roombooking.history.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.history.model.VscRoom;

import ru.roombooking.history.service.impl.VscRoomNotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vsc_room")
public class VscRoomController {
    private final VscRoomNotificationService vscRoomNotificationService;

    @GetMapping("/")
    public ResponseEntity<List<VscRoom>> findAll() {
        return ResponseEntity.ok(vscRoomNotificationService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<VscRoom> saveRoom(@RequestBody VscRoom vscRoom) {
        return ResponseEntity.ok(vscRoomNotificationService.saveRoom(vscRoom));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VscRoom> updateRoom(@RequestBody VscRoom vscRoom, @PathVariable String id) {
        return ResponseEntity.ok(vscRoomNotificationService.updateRoom(vscRoom, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<VscRoom> deleteRoom(@PathVariable String id) {
        return ResponseEntity.ok(vscRoomNotificationService.deleteRoom(id));
    }

    @GetMapping("/find-by-number-room-id/{number}")
    public ResponseEntity<VscRoom> findByNumberRoomId(@PathVariable String number) {
        return ResponseEntity.ok(vscRoomNotificationService.findByNumberRoomId(number));
    }

    @PutMapping("/batch-update-vsc-room")
    public void batchUpdateVscRoom(@RequestBody List<VscRoom> vscRoomList) {
        vscRoomNotificationService.batchUpdateVscRoom(vscRoomList);
    }
}
