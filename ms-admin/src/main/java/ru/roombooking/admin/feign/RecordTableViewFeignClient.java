package ru.roombooking.admin.feign;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.roombooking.admin.model.RecordTableView;

import java.util.List;

@FeignClient(name = "recordTableView", url = "${feign.record-table-and-vsc-room.url}", path = "/record-table-view")
public interface RecordTableViewFeignClient {
    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/")
    List<RecordTableView> findAll();

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/get-record-table-view-list-by-URL-params")
    List<RecordTableView> getRecordTableViewListByURLParams(@RequestParam String search);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/get-record-table-view-list-by-record-table-view-params",
            produces = APPLICATION_JSON_VALUE)
    List<RecordTableView> getRecordTableViewListByRecordTableViewParams(@RequestBody RecordTableView recordTableViewParams);
}