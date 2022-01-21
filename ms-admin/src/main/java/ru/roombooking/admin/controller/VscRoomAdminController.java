package ru.roombooking.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.admin.model.VscRoom;
import ru.roombooking.admin.model.dto.VscRoomRequest;
import ru.roombooking.admin.service.notification.VscRoomNotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/vscrooms")
public class VscRoomAdminController {
    private final VscRoomNotificationService vscRoomNotificationService;

    @GetMapping("/")
    public ResponseEntity<List<VscRoom>> vscRooms() {
        return ResponseEntity.ok(vscRoomNotificationService.vscRooms());
    }

    @PostMapping("/save")
    public void updateVscRoom(@RequestBody VscRoomRequest vscRoomRequest) {
        vscRoomNotificationService.updateVscRoom(vscRoomRequest);
    }

    /*@GetMapping("/delete/{id}")
    public String askDeleteRoom(@PathVariable String id, ModelMap modelMap) {
        modelMap.addAttribute("roomId", id);
        return "deleteroom";
    }*/

    @DeleteMapping("/delete/{id}")
    public void deleteRoom(@PathVariable String id) {
        vscRoomNotificationService.deleteRoom(id);
    }

    /*@GetMapping("/addroom")
    public String addRoom(ModelMap modelMap) {
        modelMap.addAttribute("roomData", new VscRoom());
        return "addingroom";
    }*/

    @PostMapping("/addroom")
    public void saveNewRoom(@RequestBody VscRoom vscRoom) {
        vscRoomNotificationService.saveNewRoom(vscRoom);
    }

}
