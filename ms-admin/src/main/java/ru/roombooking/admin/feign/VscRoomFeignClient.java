package ru.roombooking.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.roombooking.admin.model.dto.VscRoomDTO;

import java.util.List;

@PropertySource("classpath:feign-url.properties")
@FeignClient(name = "vscRoom", url = "record-table-and-vsc-room.url", path = "/vsc_room")
public interface VscRoomFeignClient {
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/")
    List<VscRoomDTO> findAll();

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET,
            value = "/find-by-number-room-id/{number}")
    VscRoomDTO findByNumberRoomId(@PathVariable String number);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT,
            value = "/batch-update-vsc-room",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void batchUpdateVscRoom(@RequestBody List<VscRoomDTO> vscRoomList);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE,
            value = "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    VscRoomDTO deleteRoom(@PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            value = "/save",
            produces = MediaType.APPLICATION_JSON_VALUE)
    VscRoomDTO saveRoom(@RequestBody VscRoomDTO vscRoom);
}