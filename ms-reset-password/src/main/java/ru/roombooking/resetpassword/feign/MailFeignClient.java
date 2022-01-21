package ru.roombooking.resetpassword.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.roombooking.resetpassword.model.dto.MailRequest;


@FeignClient(name = "mail", url = "http://localhost:8081",path = "/mail")
public interface MailFeignClient {

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, value ="/send",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    void send(@RequestBody MailRequest mailRequest);
}
