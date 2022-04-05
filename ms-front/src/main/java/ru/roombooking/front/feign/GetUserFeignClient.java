package ru.roombooking.front.feign;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.roombooking.front.model.dto.EmployeeDTO;

@FeignClient(name = "getUser", url = "${feign.employee.url}")
public interface GetUserFeignClient {
    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/get-user")
    EmployeeDTO getUser(@RequestParam String login);
}