package ru.roombooking.front.feign.admin;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.front.model.dto.DepartmentDTO;
import ru.roombooking.front.model.dto.DepartmentRequest;

import java.util.List;

@FeignClient(name = "departmentAdmin", url = "${feign.admin.url}", path = "/admin/departments")
public interface DepartmentAdminFeignClient {

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/")
    List<DepartmentDTO> departments(@RequestParam(required = false) String search);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/",
            produces = APPLICATION_JSON_VALUE)
    List<DepartmentDTO> findDepartments(@RequestBody DepartmentDTO findDepartment);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/save",
            produces = APPLICATION_JSON_VALUE)
    void updateDepartments(@RequestBody DepartmentRequest departmentRequest);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/delete/{id}")
    String askDeleteDepartment(@PathVariable String id);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = DELETE,
            value = "/delete/{id}",
            produces = APPLICATION_JSON_VALUE)
    void deleteDepartment(@PathVariable String id);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/add",
            produces = APPLICATION_JSON_VALUE)
    void saveNewDepartment(@RequestBody DepartmentDTO department);
}