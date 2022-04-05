package ru.roombooking.front.feign;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.roombooking.front.model.dto.RegistrationDTO;

@FeignClient(name = "registration", url = "${feign.registration.url}")
public interface RegistrationFeignClient {
    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/registration",
            produces = APPLICATION_JSON_VALUE)
    void userRegistration(@RequestBody RegistrationDTO registrationDTO);
}