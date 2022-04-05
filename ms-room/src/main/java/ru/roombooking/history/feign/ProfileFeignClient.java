package ru.roombooking.history.feign;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.history.model.dto.ProfileDTO;

@FeignClient(name = "profile", url = "${feign.profile.url}", path = "/profile")
public interface ProfileFeignClient {

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/find-by-login")
    ProfileDTO findByLogin(@RequestParam String login);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/find-by-id/{id}")
    ProfileDTO findById(@PathVariable String id);
}