package ru.roombooking.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.admin.model.Profile;

@FeignClient(name = "profile", url = "http://localhost:8086", path = "/profile")
public interface ProfileFeignClient {

    /*@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/")
    SuccessResponse findAll();*/

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    Profile saveProfile(@RequestBody Profile profile);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT, value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Profile updateProfile(@RequestBody Profile profile, @PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE, value = "/delete/{id}")
    Profile deleteProfile( @PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT, value = "/temp-banned")
    Profile tempBanned(@RequestParam("id") String id, @RequestParam String status);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/find-by-login")
    Profile findByLogin(@RequestParam String login);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/find-by-id/{id}")
    Profile findById(@PathVariable String id);
}
