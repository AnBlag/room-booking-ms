package ru.roombooking.history.feign;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.roombooking.history.model.dto.ProfileDTO;
import ru.roombooking.history.model.dto.EmployeeDTO;

@FeignClient(name = "employee", url = "${feign.employee.url}", path = "/employee")
public interface EmployeeFeignClient {

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/find-by-profile/{profileId}")
    EmployeeDTO findByProfileID(@PathVariable String profileId);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/find-by-login")
    EmployeeDTO findByLogin(@RequestParam String login);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "get-profile-by-id/{id}")
    ProfileDTO getProfileById(@PathVariable String id);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/find-by-id/{id}")
    EmployeeDTO findById(@PathVariable String id);
}