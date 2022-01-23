package ru.roombooking.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.admin.model.dto.ProfileDTO;

@PropertySource("classpath:feign-url.properties")
@FeignClient(name = "profile", url = "profile.url", path = "/profile")
public interface ProfileFeignClient {

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ProfileDTO saveProfile(@RequestBody ProfileDTO profile);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT, value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ProfileDTO updateProfile(@RequestBody ProfileDTO profile, @PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE, value = "/delete/{id}")
    ProfileDTO deleteProfile(@PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT, value = "/temp-banned")
    ProfileDTO tempBanned(@RequestParam("id") String id, @RequestParam String status);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/find-by-login")
    ProfileDTO findByLogin(@RequestParam String login);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/find-by-id/{id}")
    ProfileDTO findById(@PathVariable String id);
}