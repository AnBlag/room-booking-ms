package ru.roombooking.front.feign.admin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.front.model.dto.DepartmentDTO;
import ru.roombooking.front.model.dto.DepartmentRequest;

import java.util.List;

@FeignClient(name = "departmentAdmin", url = "http://localhost:8088", path = "/admin/departments")
public interface DepartmentAdminFeignClient {

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/")
    List<DepartmentDTO> departments(@RequestParam(required = false) String search);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<DepartmentDTO> findDepartments(@RequestBody DepartmentDTO findDepartment);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            value = "/save",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void updateDepartments(@RequestBody DepartmentRequest departmentRequest);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET,
            value = "/delete/{id}")
    String askDeleteDepartment(@PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE,
            value = "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteDepartment(@PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void saveNewDepartment(@RequestBody DepartmentDTO department);
}
