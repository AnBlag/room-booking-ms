package ru.roombooking.front.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.roombooking.front.model.dto.EmployeeDTO;

@FeignClient(name = "getUser", url = "http://localhost:8085")
public interface GetUserFeignClient {
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/get-user")
    EmployeeDTO getUser(@RequestParam String login);
}
