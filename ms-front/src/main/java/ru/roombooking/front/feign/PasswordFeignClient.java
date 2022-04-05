package ru.roombooking.front.feign;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.front.model.dto.ProfileDTO;

@FeignClient(name = "resetpassword", url = "${feign.reset-password.url}")
public interface PasswordFeignClient {
    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/forget-password/send/{username}",
            produces = APPLICATION_JSON_VALUE)
    void forgetPassword(@PathVariable String username);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/reset-password")
    ProfileDTO resetPassword(@RequestParam String confirmationToken);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/reset-password",
            produces = APPLICATION_JSON_VALUE)
    void saveNewPassword(@RequestBody ProfileDTO newProfileData);
}