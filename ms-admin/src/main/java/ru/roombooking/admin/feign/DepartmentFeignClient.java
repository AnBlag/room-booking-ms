package ru.roombooking.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.admin.model.dto.DepartmentDTO;

import java.util.List;

@PropertySource("classpath:feign-url.properties")
@FeignClient(name = "department", url = "department.url", path = "/department")
public interface DepartmentFeignClient {
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/find-by-id/{id}")
    DepartmentDTO findById(@PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/")
    List<DepartmentDTO> findAll();

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT, value = "/batch-update-department",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void batchUpdateDepartment(@RequestBody List<DepartmentDTO> departmentList);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE, value = "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    DepartmentDTO deleteDepartment(@PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, value = "/save",
            produces = MediaType.APPLICATION_JSON_VALUE)
    DepartmentDTO saveDepartment(@RequestBody DepartmentDTO department);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET, value = "/get-department-list-by-URL-params")
    List<DepartmentDTO> getDepartmentListByURLParams(@RequestParam String search);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            value = "/get-department-list-by-department-params",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<DepartmentDTO> getDepartmentListByDepartmentParams(@RequestBody DepartmentDTO departmentParams);
}