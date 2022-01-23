package ru.roombooking.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.admin.model.dto.EmployeeDTO;

@PropertySource("classpath:feign-url.properties")
@FeignClient(name = "employee", url = "employee.url", path = "/employee")
public interface EmployeeFeignClient {

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET,
            value = "/find-by-profile/{profileId}")
    EmployeeDTO findByProfileID(@PathVariable String profileId);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            value = "/save",
            produces = MediaType.APPLICATION_JSON_VALUE)
    EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE,
            value = "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    EmployeeDTO deleteEmployee(@PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET,
            value = "/find-by-login")
    EmployeeDTO findByLogin(@RequestParam String login);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET,
            value = "/is-present-by-department-id/{departmentId}")
    Boolean isPresentByDepartmentId(@PathVariable String departmentId);
}