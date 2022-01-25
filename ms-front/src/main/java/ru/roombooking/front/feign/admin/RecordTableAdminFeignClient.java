package ru.roombooking.front.feign.admin;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.front.model.RecordTableView;
import ru.roombooking.front.model.dto.RecordTableRequest;
import ru.roombooking.front.model.dto.RecordTableViewListAndVscRoomListDTO;

@FeignClient(name = "recordTableAdmin", url = "${feign.admin.url}", path = "/admin/records")
public interface RecordTableAdminFeignClient {

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/")
    RecordTableViewListAndVscRoomListDTO records(@RequestParam(required = false) String search);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/",
            produces = APPLICATION_JSON_VALUE)
    RecordTableViewListAndVscRoomListDTO findRecords(@RequestBody RecordTableView findRecord);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = PUT,
            value = "/save",
            produces = APPLICATION_JSON_VALUE)
    void updateRecords(@RequestBody RecordTableRequest recordTableRequest);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = DELETE,
            value = "/delete/{id}",
            produces = APPLICATION_JSON_VALUE)
    void deleteRecord(@PathVariable String id);
}