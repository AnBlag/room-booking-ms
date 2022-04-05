package ru.roombooking.resetpassword.feign;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.resetpassword.model.dto.ProfileDTO;

@FeignClient(name = "profile", url = "${feign.profile.url}", path = "/profile")
public interface ProfileFeignClient {

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/save",
            produces = APPLICATION_JSON_VALUE)
    ProfileDTO saveProfile(@RequestBody ProfileDTO profile);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = PUT,
            value = "/update/{id}",
            produces = APPLICATION_JSON_VALUE)
    ProfileDTO updateProfile(@RequestBody ProfileDTO profile, @PathVariable String id);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = DELETE,
            value = "/delete/{id}",
            produces = APPLICATION_JSON_VALUE)
    ProfileDTO deleteProfile(@PathVariable String id);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = PUT,
            value = "/temp-banned",
            produces = APPLICATION_JSON_VALUE)
    ProfileDTO tempBanned(@RequestParam("id") String id, @RequestParam String status);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/find-by-login")
    ProfileDTO findByLogin(@RequestParam String login);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/find-by-id/{id}")
    ProfileDTO findById(@PathVariable String id);
}