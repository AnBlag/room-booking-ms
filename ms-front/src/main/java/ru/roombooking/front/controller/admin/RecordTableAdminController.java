package ru.roombooking.front.controller.admin;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.front.exception.RecordTableAndVscRoomRequestException;
import ru.roombooking.front.exception.RecordTableRequestException;
import ru.roombooking.front.feign.admin.RecordTableAdminFeignClient;
import ru.roombooking.front.model.RecordTableView;
import ru.roombooking.front.model.dto.RecordTableRequest;
import ru.roombooking.front.model.dto.RecordTableViewListAndVscRoomListDTO;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/records")
public class RecordTableAdminController {
    private final RecordTableAdminFeignClient recordTableAdminFeignClient;

    @GetMapping("/")
    public String records(@RequestParam(value = "search", required = false) String search, ModelMap modelMap) {
        log.info("Поиск бронирований");
        RecordTableViewListAndVscRoomListDTO request;
        try {
            request = recordTableAdminFeignClient.records(search);
        } catch (FeignException e) {
            throw new RecordTableAndVscRoomRequestException();
        }

        modelMap.addAttribute("recordTableViewList", request.getRecordTableViewList());
        modelMap.addAttribute("findRecord", new RecordTableView());
        modelMap.addAttribute("vscroomlist", request.getVscRoomList());

        return "recordadminpage";
    }

    @PostMapping("/")
    public String findRecords(@ModelAttribute("findRecord") RecordTableView findRecord, ModelMap modelMap) {
        log.info("Поиск бронирований по параметрам");
        RecordTableViewListAndVscRoomListDTO request;
        try {
            request = recordTableAdminFeignClient.findRecords(findRecord);
        } catch (FeignException e) {
            throw new RecordTableAndVscRoomRequestException();
        }

        modelMap.addAttribute("recordTableViewList", request.getRecordTableViewList());

        modelMap.addAttribute("vscroomlist", request.getVscRoomList());

        return "recordadminpage";
    }

    @PostMapping("/save")
    public String updateRecords(@RequestParam(name = "id") String id,
                                @RequestParam(name = "email") String email,
                                @RequestParam(name = "employeeId") String employeeId,
                                @RequestParam(name = "vcsRoomNumberRoom") String vcsRoomNumberRoom,
                                @RequestParam(name = "isActive") String isActive,
                                @RequestParam(name = "title") String title,
                                @RequestParam(name = "startEvent") String startEvent,
                                @RequestParam(name = "endEvent") String endEvent
    ) {
        log.info("Обновление бронирований");
        try {
            recordTableAdminFeignClient.updateRecords(new RecordTableRequest(id,
                    email,
                    employeeId,
                    vcsRoomNumberRoom,
                    isActive,
                    title,
                    startEvent,
                    endEvent));
        } catch (FeignException e) {
            throw new RecordTableRequestException();
        }
        log.info("Обновление бронирований успешно завершено");

        return "redirect:/admin/records/";
    }

    @GetMapping("/delete/{id}")
    public String askToDeleteRecord(@PathVariable String id, ModelMap modelMap) {
        log.info("Запрос на удаление бронирования");
        modelMap.addAttribute("recordTableId", id);
        return "deleterecord";
    }

    @PostMapping("/delete/{id}")
    public String deleteRecord(@PathVariable String id) {
        log.info("Удаление бронирования");
        try {
            recordTableAdminFeignClient.deleteRecord(id);
        } catch (FeignException e) {
            throw new RecordTableRequestException();
        }
        log.info("Удаление бронирования успешно завершено");

        return "redirect:/admin/records/";
    }
}