package ru.roombooking.front.controller.admin;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.front.exception.VscRoomRequestException;
import ru.roombooking.front.feign.admin.VscRoomAdminFeignClient;
import ru.roombooking.front.model.dto.VscRoomDTO;
import ru.roombooking.front.model.dto.VscRoomRequest;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/vscrooms")
public class VscRoomAdminController {
    private final VscRoomAdminFeignClient vscRoomAdminFeignClient;

    @GetMapping("/")
    public String vscRooms(ModelMap modelMap) {
        log.info("Поиск комнат");
        List<VscRoomDTO> vscRoomList;
        try {
            vscRoomList = vscRoomAdminFeignClient.vscRooms();
        } catch (FeignException e) {
            throw new VscRoomRequestException();
        }
        modelMap.addAttribute("vscRoomList", vscRoomList);

        return "vscroomadminpage";
    }

    @PostMapping("/save")
    public String updateVscRoom(@RequestParam(name = "id") String id,
                                @RequestParam(name = "isActive") String isActive,
                                @RequestParam(name = "numberRoom") String numberRoom
    ) {
        log.info("Сохранение комнат");
        try {
            vscRoomAdminFeignClient.updateVscRoom(new VscRoomRequest(id, isActive, numberRoom));
        } catch (FeignException e) {
            throw new VscRoomRequestException();
        }

        return "redirect:/admin/vscrooms/";
    }

    @GetMapping("/delete/{id}")
    public String askToDeleteRoom(@PathVariable String id, ModelMap modelMap) {
        log.info("Запрос на удаление комнаты");
        modelMap.addAttribute("roomId", id);
        return "deleteroom";
    }

    @PostMapping("/delete/{id}")
    public String deleteRoom(@PathVariable String id) {
        log.info("Удаление комнаты");
        try {
            vscRoomAdminFeignClient.deleteRoom(id);
        } catch (FeignException e) {
            throw new VscRoomRequestException();
        }
        log.info("Удаление комнаты успешно завершено");

        return "redirect:/admin/vscrooms/";
    }

    @GetMapping("/addroom")
    public String addRoom(ModelMap modelMap) {
        log.info("Запрос на добавление новой комнаты");
        modelMap.addAttribute("roomData", new VscRoomDTO());
        return "addingroom";
    }

    @PostMapping("/addroom")
    public String saveNewRoom(@ModelAttribute("roomData") final @Valid VscRoomDTO vscRoom) {
        log.info("Добавление новой комнаты");
        try {
            vscRoomAdminFeignClient.saveNewRoom(vscRoom);
        } catch (FeignException e) {
            throw new VscRoomRequestException();
        }
        log.info("Добавление новой комнаты успешно завершено");

        return "redirect:/admin/vscrooms/";
    }
}