package ru.roombooking.front.feign.admin;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.front.model.EmployeeView;
import ru.roombooking.front.model.dto.*;

import java.util.List;

@FeignClient(name = "employeeAdmin", url = "${feign.admin.url}", path = "/admin/employees")
public interface EmployeeAdminFeignClient {

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/save",
            produces = APPLICATION_JSON_VALUE)
    void updateUsers(@RequestBody EmployeeRequest employeeRequest);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/")
    List<EmployeeView> employees(@RequestParam(required = false) String search);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/",
            produces = APPLICATION_JSON_VALUE)
    List<EmployeeView> findEmployees(@RequestBody EmployeeView employeeView);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/edit/{id}")
    EmployeeEditDTO editEmployee(@PathVariable String id);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/edit/",
            produces = APPLICATION_JSON_VALUE)
    void saveEmployee(@RequestBody EmployeeSaveDTO employeeSaveDTO);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = DELETE,
            value = "/delete/{id}",
            produces = APPLICATION_JSON_VALUE)
    void deleteEmployee(@PathVariable String id);
}