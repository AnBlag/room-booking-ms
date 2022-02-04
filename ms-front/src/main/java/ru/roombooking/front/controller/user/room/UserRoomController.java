package ru.roombooking.front.controller.user.room;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.roombooking.front.exception.VscRoomRequestException;
import ru.roombooking.front.feign.VscRoomFeignClient;

@Controller
@AllArgsConstructor
@Slf4j
public class UserRoomController {
    private final VscRoomFeignClient vscRoomFeignClient;

    @GetMapping("/room/{idRoom}")
    public String calendar(@PathVariable String idRoom, ModelMap modelMap) {

        log.info("Поиск всех комнат");
        try {
            vscRoomFeignClient.findByNumberRoomId(idRoom);
            modelMap.addAttribute("vscroomlist", vscRoomFeignClient.findAll());
        } catch (FeignException e) {
            throw new VscRoomRequestException();
        }
        return "calendar";
    }
}