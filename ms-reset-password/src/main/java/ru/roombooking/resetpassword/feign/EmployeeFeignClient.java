package ru.roombooking.resetpassword.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.roombooking.resetpassword.model.dto.EmployeeDTO;

@FeignClient(name = "employee", url = "http://localhost:8085", path = "/employee")
public interface EmployeeFeignClient {

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET,
                    value ="/find-by-profile/{profileId}")
    EmployeeDTO findByProfileID(@PathVariable String profileId);
}
