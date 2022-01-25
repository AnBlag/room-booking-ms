package ru.roombooking.front.controller;

import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.roombooking.front.exception.VscRoomBadRequestException;
import ru.roombooking.front.feign.VscRoomFeignClient;

@Controller
@AllArgsConstructor
public class MainPageController {
    private final VscRoomFeignClient vscRoomFeignClient;

    @GetMapping("/")
    public String indexPage(ModelMap modelMap) {
        try {
            modelMap.addAttribute("vscroomlist", vscRoomFeignClient.findAll());
        } catch (FeignException e) {
            throw new VscRoomBadRequestException();
        }

        return "index";
    }
}