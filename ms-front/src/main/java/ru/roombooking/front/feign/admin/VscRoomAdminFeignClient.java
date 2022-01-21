package ru.roombooking.front.feign.admin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.roombooking.front.model.dto.VscRoomDTO;
import ru.roombooking.front.model.dto.VscRoomRequest;

import java.util.List;

@FeignClient(name = "adminVscRoom", url = "http://localhost:8088", path = "/admin/vscrooms")
public interface VscRoomAdminFeignClient {

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET,
            value = "/")
    List<VscRoomDTO> vscRooms();

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            value = "/save",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void updateVscRoom(@RequestBody VscRoomRequest vscRoomRequest);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE,
            value = "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteRoom(@PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            value = "/addroom",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void saveNewRoom(@RequestBody VscRoomDTO vscRoom);
}
