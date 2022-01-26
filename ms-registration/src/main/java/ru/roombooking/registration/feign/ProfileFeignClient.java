package ru.roombooking.registration.feign;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.registration.model.Profile;

@FeignClient(name = "profile", url = "${feign.profile.url}", path = "/profile")
public interface ProfileFeignClient {

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/save",
            produces = APPLICATION_JSON_VALUE)
    Profile saveProfile(@RequestBody Profile profile);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = PUT,
            value = "/update/{id}",
            produces = APPLICATION_JSON_VALUE)
    Profile updateProfile(@RequestBody Profile profile, @PathVariable String id);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = DELETE,
            value = "/delete/{id}",
            produces = APPLICATION_JSON_VALUE)
    Profile deleteProfile(@PathVariable String id);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = PUT,
            value = "/temp-banned",
            produces = APPLICATION_JSON_VALUE)
    Profile tempBanned(@RequestParam("id") String id, @RequestParam String status);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/find-by-login")
    Profile findByLogin(@RequestParam String login);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/find-by-id/{id}")
    Profile findById(@PathVariable String id);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/does-profile-exist")
    Boolean doesProfileExist(@RequestParam String login);
}