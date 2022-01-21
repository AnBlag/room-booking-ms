package ru.roombooking.front.controller.admin;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.front.exception.RecordTableAndVscRoomBadRequestException;
import ru.roombooking.front.exception.RecordTableBadRequestException;
import ru.roombooking.front.feign.admin.RecordTableAdminFeignClient;
import ru.roombooking.front.model.RecordTableView;
import ru.roombooking.front.model.dto.RecordTableRequest;
import ru.roombooking.front.model.dto.RecordTableViewListAndVscRoomListRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/records")
public class RecordTableAdminController {
    private final RecordTableAdminFeignClient recordTableAdminFeignClient;

    @GetMapping("/")
    public String records(@RequestParam(value = "search", required = false) String search,
                          ModelMap modelMap) {
        RecordTableViewListAndVscRoomListRequest request;
        try {
            request = recordTableAdminFeignClient.records(search);
        } catch (FeignException e) {
            throw new RecordTableAndVscRoomBadRequestException();
        }

        modelMap.addAttribute("recordTableViewList", request.getRecordTableViewList());
        modelMap.addAttribute("findRecord",new RecordTableView());
        modelMap.addAttribute("vscroomlist", request.getVscRoomList());

        return "recordadminpage";
    }

    @PostMapping("/")
    public String findRecords(@ModelAttribute("findRecord") RecordTableView findRecord,
                                  ModelMap modelMap) {
        RecordTableViewListAndVscRoomListRequest request;
        try {
            request = recordTableAdminFeignClient.findRecords(findRecord);
        } catch (FeignException e) {
            throw new RecordTableAndVscRoomBadRequestException();
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
            throw new RecordTableBadRequestException();
        }

        return "redirect:/admin/records/";
    }

    @GetMapping("/delete/{id}")
    public String askDeleteRecord(@PathVariable String id, ModelMap modelMap) {
        modelMap.addAttribute("recordTableId", id);
        return "deleterecord";
    }

    @PostMapping("/delete/{id}")
    public String deleteRecord(@PathVariable String id) {
        try {
            recordTableAdminFeignClient.deleteRecord(id);
        } catch (FeignException e) {
            throw new RecordTableBadRequestException();
        }

        return "redirect:/admin/records/";
    }

}