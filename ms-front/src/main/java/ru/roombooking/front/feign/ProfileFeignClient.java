package ru.roombooking.front.feign;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.front.model.dto.ProfileDTO;

@FeignClient(name = "profile", url = "http://localhost:8086", path = "/profile")
public interface ProfileFeignClient {

    /*@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/")
    SuccessResponse findAll();*/

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ProfileDTO saveProfile(@RequestBody ProfileDTO profileDTO);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT, value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ProfileDTO updateProfile(@RequestBody ProfileDTO profileDTO, @PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE, value = "/delete/{id}")
    ProfileDTO deleteProfile( @PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT, value = "/temp-banned")
    ProfileDTO tempBanned(@RequestParam("id") String id, @RequestParam String status);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/find-by-login")
    ProfileDTO findByLogin(@RequestParam String login);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/find-by-id/{id}")
    ProfileDTO findById(@PathVariable String id);
}
