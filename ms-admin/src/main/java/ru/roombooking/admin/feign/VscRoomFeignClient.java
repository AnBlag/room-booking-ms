package ru.roombooking.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.roombooking.admin.model.VscRoom;

import java.util.List;

@FeignClient(name = "vscRoom", url = "http://localhost:8087", path = "/vsc_room")
public interface VscRoomFeignClient {
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/")
    List<VscRoom> findAll();

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET,
                    value = "/find-by-number-room-id/{number}")
    VscRoom findByNumberRoomId(@PathVariable String number);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT,
            value = "/batch-update-vsc-room",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void batchUpdateVscRoom(@RequestBody List<VscRoom> vscRoomList);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE,
            value = "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    VscRoom deleteRoom(@PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            value = "/save",
            produces = MediaType.APPLICATION_JSON_VALUE)
    VscRoom saveRoom(@RequestBody VscRoom vscRoom);
}
