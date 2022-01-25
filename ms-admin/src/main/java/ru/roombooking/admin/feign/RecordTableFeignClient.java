package ru.roombooking.admin.feign;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.roombooking.admin.model.dto.RecordTableDTO;

import java.util.List;

@FeignClient(name = "recordTable", url = "${feign.record-table-and-vsc-room.url}", path = "/record")
public interface RecordTableFeignClient {
    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/")
    List<RecordTableDTO> findAll();

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/{index}")
    List<RecordTableDTO> findByIndex(@PathVariable String index);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/findAll")
    List<RecordTableDTO> findAllByEmployeeFullNameAndRecordAndIsActiveAndNumberRoom();

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/save/{login}",
            produces = APPLICATION_JSON_VALUE)
    RecordTableDTO saveRecord(@RequestBody RecordTableDTO recordTableDTO,
                              @PathVariable String login);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = PUT,
            value = "/update/{login}",
            produces = APPLICATION_JSON_VALUE)
    RecordTableDTO updateRecord(@RequestBody RecordTableDTO recordTableDTO,
                                @PathVariable String login);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = DELETE,
            value = "/delete/{login}",
            produces = APPLICATION_JSON_VALUE)
    RecordTableDTO deleteRecord(@RequestBody RecordTableDTO recordTableDTO,
                                @PathVariable String login);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = DELETE,
            value = "/delete-by-id/{id}",
            produces = APPLICATION_JSON_VALUE)
    RecordTableDTO deleteRecordById(@PathVariable String id);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = PUT,
            value = "/batch-update-records",
            produces = APPLICATION_JSON_VALUE)
    void batchUpdateRecords(@RequestBody List<RecordTableDTO> recordTableList);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/is-overlapping-record")
    Boolean isOverlappingRecord(@RequestBody RecordTableDTO recordTableDTO);
}