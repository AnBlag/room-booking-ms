package ru.roombooking.front.controller.admin;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/admin/vscrooms")
public class VscRoomAdminController {
    private final VscRoomAdminFeignClient vscRoomAdminFeignClient;

    @GetMapping("/")
    public String vscRooms(ModelMap modelMap) {
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
        try {
            vscRoomAdminFeignClient.updateVscRoom(new VscRoomRequest(id, isActive, numberRoom));
        } catch (FeignException e) {
            throw new VscRoomRequestException();
        }

        return "redirect:/admin/vscrooms/";
    }

    @GetMapping("/delete/{id}")
    public String askToDeleteRoom(@PathVariable String id, ModelMap modelMap) {
        modelMap.addAttribute("roomId", id);
        return "deleteroom";
    }

    @PostMapping("/delete/{id}")
    public String deleteRoom(@PathVariable String id) {
        try {
            vscRoomAdminFeignClient.deleteRoom(id);
        } catch (FeignException e) {
            throw new VscRoomRequestException();
        }

        return "redirect:/admin/vscrooms/";
    }

    @GetMapping("/addroom")
    public String addRoom(ModelMap modelMap) {
        modelMap.addAttribute("roomData", new VscRoomDTO());
        return "addingroom";
    }

    @PostMapping("/addroom")
    public String saveNewRoom(@ModelAttribute("roomData") final @Valid VscRoomDTO vscRoom) {
        try {
            vscRoomAdminFeignClient.saveNewRoom(vscRoom);
        } catch (FeignException e) {
            throw new VscRoomRequestException();
        }

        return "redirect:/admin/vscrooms/";
    }
}