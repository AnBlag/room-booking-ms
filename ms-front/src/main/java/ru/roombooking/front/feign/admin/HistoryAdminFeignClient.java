package ru.roombooking.front.feign.admin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.roombooking.front.model.RecordTableView;
import ru.roombooking.front.model.dto.RecordTableDTO;
import ru.roombooking.front.model.dto.RecordTableRequest;
import ru.roombooking.front.model.dto.RecordTableViewListAndVscRoomListDTO;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@FeignClient(name = "historyAdmin", url = "${feign.admin.url}", path = "/admin/history")
public interface HistoryAdminFeignClient {

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/")
    List<RecordTableDTO> records();

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = DELETE,
            value = "/delete/{id}",
            produces = APPLICATION_JSON_VALUE)
    RecordTableDTO deleteById(@PathVariable String id);
}