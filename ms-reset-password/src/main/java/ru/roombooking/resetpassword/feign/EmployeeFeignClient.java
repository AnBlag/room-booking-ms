package ru.roombooking.resetpassword.feign;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.roombooking.resetpassword.model.dto.EmployeeDTO;

@FeignClient(name = "employee", url = "${feign.employee.url}", path = "/employee")
public interface EmployeeFeignClient {

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/find-by-profile/{profileId}")
    EmployeeDTO findByProfileID(@PathVariable String profileId);
}