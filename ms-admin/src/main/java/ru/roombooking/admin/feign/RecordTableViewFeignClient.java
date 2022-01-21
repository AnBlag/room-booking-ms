package ru.roombooking.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.roombooking.admin.model.RecordTableView;

import java.util.List;


@FeignClient(name = "recordTableView", url = "http://localhost:8087", path = "/record-table-view")
public interface RecordTableViewFeignClient {
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET,
                    value = "/")
    List<RecordTableView> findAll();

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET,
            value = "/get-record-table-view-list-by-URL-params")
    List<RecordTableView> getRecordTableViewListByURLParams(@RequestParam String search);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            value = "/get-record-table-view-list-by-record-table-view-params",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<RecordTableView> getRecordTableViewListByRecordTableViewParams(@RequestBody RecordTableView recordTableViewParams);
}
