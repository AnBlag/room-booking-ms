package ru.roombooking.front.feign;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.roombooking.front.model.dto.VscRoomDTO;

import java.util.List;

@FeignClient(name = "vscRoom", url = "${feign.record-table-and-vsc-room.url}", path = "/vsc_room")
public interface VscRoomFeignClient {
    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/")
    List<VscRoomDTO> findAll();

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/find-by-number-room-id/{number}")
    VscRoomDTO findByNumberRoomId(@PathVariable String number);
}