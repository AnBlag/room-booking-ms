package ru.roombooking.front.feign.admin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.front.model.RecordTableView;
import ru.roombooking.front.model.dto.RecordTableDTO;
import ru.roombooking.front.model.dto.RecordTableRequest;
import ru.roombooking.front.model.dto.RecordTableViewListAndVscRoomListRequest;

import java.util.List;


@FeignClient(name = "recordTableAdmin", url = "http://localhost:8088", path = "/admin/records")
public interface RecordTableAdminFeignClient {

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET,
                    value = "/")
    RecordTableViewListAndVscRoomListRequest records(@RequestParam(required = false) String search);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    RecordTableViewListAndVscRoomListRequest findRecords(@RequestBody RecordTableView findRecord);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT,
            value = "/save",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void updateRecords(@RequestBody RecordTableRequest recordTableRequest);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.DELETE,
                    value = "/delete/{id}",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteRecord(@PathVariable String id);

}
