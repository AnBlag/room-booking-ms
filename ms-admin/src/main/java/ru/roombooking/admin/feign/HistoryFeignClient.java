package ru.roombooking.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.roombooking.admin.model.dto.RecordTableDTO;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@FeignClient(name = "history", url = "${feign.record-table-and-vsc-room.url}", path = "/history")
public interface HistoryFeignClient {
    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/")
    List<RecordTableDTO> findAll();

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = DELETE,
            value = "/delete-by-id/{id}",
            produces = APPLICATION_JSON_VALUE)
    RecordTableDTO deleteRecordById(@PathVariable String id);
}