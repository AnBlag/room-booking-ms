package ru.roombooking.employee.feign;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.roombooking.employee.model.dto.ProfileDTO;

@FeignClient(name = "profile", url = "${feign.profile.url}", path = "/profile")
public interface ProfileFeignClient {

    @RequestMapping(consumes = APPLICATION_JSON_VALUE, method = GET, value = "/find-by-id/{id}")
    ProfileDTO findById(@PathVariable String id);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE, method = GET, value = "/find-by-login")
    ProfileDTO findByLogin(@RequestParam String login);
}