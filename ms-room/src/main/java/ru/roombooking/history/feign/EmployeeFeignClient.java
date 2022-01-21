package ru.roombooking.history.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.roombooking.history.model.Profile;
import ru.roombooking.history.model.dto.EmployeeDTO;


@FeignClient(name = "employee", url = "http://localhost:8085", path = "/employee")
public interface EmployeeFeignClient {

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET,
                    value ="/find-by-profile/{profileId}")
    EmployeeDTO findByProfileID(@PathVariable String profileId);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET,
            value ="/find-by-login")
    EmployeeDTO findByLogin(@RequestParam String login);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET,
            value ="get-profile-by-id/{id}")
    Profile getProfileById(@PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET,
            value ="/find-by-id/{id}")
    EmployeeDTO findById(@PathVariable String id);
}
