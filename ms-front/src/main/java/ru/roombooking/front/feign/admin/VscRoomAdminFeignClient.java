package ru.roombooking.front.feign.admin;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.roombooking.front.model.dto.VscRoomDTO;
import ru.roombooking.front.model.dto.VscRoomRequest;

import java.util.List;

@FeignClient(name = "adminVscRoom", url = "${feign.admin.url}", path = "/admin/vscrooms")
public interface VscRoomAdminFeignClient {

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/")
    List<VscRoomDTO> vscRooms();

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/save",
            produces = APPLICATION_JSON_VALUE)
    void updateVscRoom(@RequestBody VscRoomRequest vscRoomRequest);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = DELETE,
            value = "/delete/{id}",
            produces = APPLICATION_JSON_VALUE)
    void deleteRoom(@PathVariable String id);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/addroom",
            produces = APPLICATION_JSON_VALUE)
    void saveNewRoom(@RequestBody VscRoomDTO vscRoom);
}