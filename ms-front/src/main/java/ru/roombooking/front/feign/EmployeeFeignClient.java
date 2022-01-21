package ru.roombooking.front.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.front.model.dto.EmployeeDTO;

@FeignClient(name = "employee", url = "http://localhost:8085", path = "/employee")
public interface EmployeeFeignClient {

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET,
                    value ="/find-by-profile/{profileId}")
    EmployeeDTO findByProfileID(@PathVariable String profileId);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST,
                    value = "/save",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET,
                    value ="/find-by-login")
    EmployeeDTO findByLogin(@RequestParam String login);
}
