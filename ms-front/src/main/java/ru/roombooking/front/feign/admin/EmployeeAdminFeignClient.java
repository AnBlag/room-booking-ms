package ru.roombooking.front.feign.admin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.front.model.EmployeeView;
import ru.roombooking.front.model.dto.*;

import java.util.List;

@FeignClient(name = "employeeAdmin", url = "http://localhost:8088", path = "/admin/employees")
public interface EmployeeAdminFeignClient {

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            value = "/save",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void updateUsers(@RequestBody EmployeeRequest employeeRequest);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET,
            value = "/")
    List<EmployeeView> employees(@RequestParam(required = false) String search);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<EmployeeView> findEmployees(@RequestBody EmployeeView employeeView);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET,
            value = "/edit/{id}")
    EmployeeEditRequest editEmployee(@PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            value = "/edit/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void saveEmployee(@RequestBody EmployeeSaveRequest employeeSaveRequest);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE,
            value = "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteEmployee(@PathVariable String id);
}
