package ru.roombooking.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.admin.model.Department;

import java.util.List;

@FeignClient(name = "department", url = "http://localhost:8084", path = "/department")
public interface DepartmentFeignClient {
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/find-by-id/{id}")
    Department findById(@PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/")
    List<Department> findAll();

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT, value = "/batch-update-department",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void batchUpdateDepartment(@RequestBody List<Department> departmentList);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE, value = "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    Department deleteDepartment(@PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, value = "/save",
            produces = MediaType.APPLICATION_JSON_VALUE)
    Department saveDepartment(@RequestBody Department department);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET, value = "/get-department-list-by-URL-params")
    List<Department> getDepartmentListByURLParams(@RequestParam String search);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            value = "/get-department-list-by-department-params",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<Department> getDepartmentListByDepartmentParams(@RequestBody Department departmentParams);
}