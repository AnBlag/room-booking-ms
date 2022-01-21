package ru.roombooking.history.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.history.model.Profile;


@FeignClient(name = "profile", url = "http://localhost:8086", path = "/profile")
public interface ProfileFeignClient {

    /*@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/")
    SuccessResponse findAll();*/


    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/find-by-login")
    Profile findByLogin(@RequestParam String login);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/find-by-id/{id}")
    Profile findById(@PathVariable String id);
}
