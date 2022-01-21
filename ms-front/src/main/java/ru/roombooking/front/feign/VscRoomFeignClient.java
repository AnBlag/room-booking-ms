package ru.roombooking.front.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.roombooking.front.model.dto.VscRoomDTO;

import java.util.List;

@FeignClient(name = "vscRoom", url = "http://localhost:8087", path = "/vsc_room")
public interface VscRoomFeignClient {
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/")
    List<VscRoomDTO> findAll();

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET,
                    value = "/find-by-number-room-id/{number}")
    VscRoomDTO findByNumberRoomId(@PathVariable String number);
}
