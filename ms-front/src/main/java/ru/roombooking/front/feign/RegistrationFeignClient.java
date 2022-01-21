package ru.roombooking.front.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.roombooking.front.model.dto.RegistrationDTO;

@FeignClient(name = "registration", url = "http://localhost:8082")
public interface RegistrationFeignClient {
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST,
                    value = "/registration",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    void userRegistration(@RequestBody RegistrationDTO registrationDTO);



}
