package ru.roombooking.front.controller;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.roombooking.front.feign.VscRoomFeignClient;

@Controller
@AllArgsConstructor
@Slf4j
public class MainPageController {
    private final VscRoomFeignClient vscRoomFeignClient;

    @GetMapping("/")
    public String indexPage(ModelMap modelMap) {
        log.info("Поиск всех комнат");
        try {
            modelMap.addAttribute("vscroomlist", vscRoomFeignClient.findAll());
        } catch (FeignException e) {
            log.info("Ошибка загрузки комнат");
        }

        return "index";
    }
}