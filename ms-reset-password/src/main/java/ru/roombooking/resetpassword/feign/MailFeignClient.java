package ru.roombooking.resetpassword.feign;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.roombooking.resetpassword.model.dto.MailParams;

@FeignClient(name = "mail", url = "${feign.mail.url}", path = "/mail")
public interface MailFeignClient {

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/send",
            produces = APPLICATION_JSON_VALUE)
    void send(@RequestBody MailParams mailParams);
}