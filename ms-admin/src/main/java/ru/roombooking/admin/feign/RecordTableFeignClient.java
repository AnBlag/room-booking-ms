package ru.roombooking.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.roombooking.admin.model.RecordTable;
import ru.roombooking.admin.model.dto.RecordTableDTO;

import java.util.List;


@FeignClient(name = "recordTable", url = "http://localhost:8087", path = "/record")
public interface RecordTableFeignClient {
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET,
                    value = "/")
    List<RecordTableDTO> findAll();

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET,
                    value = "/{index}")
    List<RecordTableDTO> findByIndex(@PathVariable String index);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET,
                    value = "/findAll")
    List<RecordTableDTO> findAllByEmployeeFullNameAndRecordAndIsActiveAndNumberRoom();

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST,
                    value = "/save/{login}",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    RecordTableDTO saveRecord(@RequestBody RecordTableDTO recordTableDTO,
                                             @PathVariable String login);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.PUT,
                    value = "/update/{login}",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    RecordTableDTO updateRecord(@RequestBody RecordTableDTO recordTableDTO,
                                               @PathVariable String login);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.DELETE,
                    value = "/delete/{login}",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    RecordTableDTO deleteRecord(@RequestBody RecordTableDTO recordTableDTO,
                                               @PathVariable String login);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE,
            value = "/delete-by-id/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    RecordTableDTO deleteRecordById(@PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT,
            value = "/batch-update-records",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void batchUpdateRecords(@RequestBody List<RecordTable> recordTableList);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET,
            value = "/is-overlapping-record")
    Boolean isOverlappingRecord(@RequestBody RecordTableDTO recordTableDTO);
}
