package ru.roombooking.history.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(name = "security", url = "http://localhost:8080", path = "/security")
public interface SecurityFeignClient {

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET,
                    value ="/is-admin")
    Boolean isAdmin(@RequestParam String login);

    /*@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET,
            value ="/get-user-auth")
    LoginSuccessResponse getUserAuth();*/
}
