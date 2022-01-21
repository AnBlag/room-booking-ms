package ru.roombooking.front.controller.user.room;

import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.roombooking.front.exception.VscRoomBadRequestException;
import ru.roombooking.front.feign.VscRoomFeignClient;

@Controller
@AllArgsConstructor
public class UserRoomController {
    private final VscRoomFeignClient vscRoomFeignClient;

    @GetMapping("/room/{idRoom}")
    public String calendar(@PathVariable String idRoom, ModelMap modelMap) {

        try {
            vscRoomFeignClient.findByNumberRoomId(idRoom);
            modelMap.addAttribute("vscroomlist", vscRoomFeignClient.findAll());
            return "calendar";
        }
        catch (FeignException e) {
            throw new VscRoomBadRequestException();
        }
    }

}
