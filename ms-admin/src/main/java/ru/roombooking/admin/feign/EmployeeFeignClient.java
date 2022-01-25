package ru.roombooking.admin.feign;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.admin.model.dto.EmployeeDTO;

@FeignClient(name = "employee", url = "${feign.employee.url}", path = "/employee")
public interface EmployeeFeignClient {

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/find-by-profile/{profileId}")
    EmployeeDTO findByProfileID(@PathVariable String profileId);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/save",
            produces = APPLICATION_JSON_VALUE)
    EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = DELETE,
            value = "/delete/{id}",
            produces = APPLICATION_JSON_VALUE)
    EmployeeDTO deleteEmployee(@PathVariable String id);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/find-by-login")
    EmployeeDTO findByLogin(@RequestParam String login);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/is-present-by-department-id/{departmentId}")
    Boolean isPresentByDepartmentId(@PathVariable String departmentId);
}