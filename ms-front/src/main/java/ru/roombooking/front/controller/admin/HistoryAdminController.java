package ru.roombooking.front.controller.admin;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.roombooking.front.exception.RecordTableRequestException;
import ru.roombooking.front.feign.admin.HistoryAdminFeignClient;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/history")
public class HistoryAdminController {
    private final HistoryAdminFeignClient historyAdminFeignClient;

    @GetMapping("/")
    public String records(ModelMap modelMap) {
        log.info("Поиск бронирований в истории");

        try {
            modelMap.addAttribute("recordTableDTOList", historyAdminFeignClient.records());
        } catch (FeignException e) {
            throw new RecordTableRequestException();
        }

        return "historyadminpage";
    }
}