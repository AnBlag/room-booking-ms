package ru.roombooking.front.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;
import ru.roombooking.front.model.dto.ProfileDTO;

@FeignClient(name = "resetpassword", url = "http://localhost:8083")
public interface PasswordFeignClient {
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, value = "/forget-password/send/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    void forgetPassword(@PathVariable String username);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/reset-password")
    ProfileDTO resetPassword(@RequestParam String confirmationToken);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, value = "/reset-password", produces = MediaType.APPLICATION_JSON_VALUE)
    void saveNewPassword(@RequestBody ProfileDTO newProfileData);

}
