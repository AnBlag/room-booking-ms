package ru.roombooking.history.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.roombooking.history.model.dto.PreviousAndCurrentRecordTableDTO;
import ru.roombooking.history.model.dto.RecordTableDTO;



@FeignClient(name = "mail", url = "http://localhost:8081",path = "/mail")
public interface MailFeignClient {

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, value ="/send-confirmation/{roomId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void sendConfirmMessageToEmployee(@RequestBody RecordTableDTO recordTableDTO,
                                                 @PathVariable String roomId);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, value ="/send-confirmation/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void sendConfirmUpdateMessageToEmployee(@RequestBody PreviousAndCurrentRecordTableDTO previousAndCurrentRecordTableDTO);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, value ="/delete-confirmation/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void sendConfirmDeleteMessageToEmployee(@RequestBody RecordTableDTO recordTableDTO);

}
