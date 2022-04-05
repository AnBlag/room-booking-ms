package ru.roombooking.history.feign;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.roombooking.history.model.dto.PreviousAndCurrentRecordTableDTO;
import ru.roombooking.history.model.dto.RecordTableDTO;

@FeignClient(name = "mail", url = "${feign.mail.url}", path = "/mail")
public interface MailFeignClient {

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/send-confirmation/{roomId}",
            produces = APPLICATION_JSON_VALUE)
    void sendConfirmMessageToEmployee(@RequestBody RecordTableDTO recordTableDTO,
                                      @PathVariable String roomId);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/send-confirmation/",
            produces = APPLICATION_JSON_VALUE)
    void sendConfirmUpdateMessageToEmployee(@RequestBody PreviousAndCurrentRecordTableDTO previousAndCurrentRecordTableDTO);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/delete-confirmation/",
            produces = APPLICATION_JSON_VALUE)
    void sendConfirmDeleteMessageToEmployee(@RequestBody RecordTableDTO recordTableDTO);
}