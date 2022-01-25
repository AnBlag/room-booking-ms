package ru.roombooking.admin.feign;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.roombooking.admin.model.dto.DepartmentDTO;

import java.util.List;

@FeignClient(name = "department", url = "${feign.department.url}", path = "/department")
public interface DepartmentFeignClient {
    @RequestMapping(consumes = APPLICATION_JSON_VALUE, method = GET, value = "/find-by-id/{id}")
    DepartmentDTO findById(@PathVariable String id);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE, method = GET, value = "/")
    List<DepartmentDTO> findAll();

    @RequestMapping(consumes = APPLICATION_JSON_VALUE, method = PUT, value = "/batch-update-department",
            produces = APPLICATION_JSON_VALUE)
    void batchUpdateDepartment(@RequestBody List<DepartmentDTO> departmentList);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE, method = DELETE, value = "/delete/{id}",
            produces = APPLICATION_JSON_VALUE)
    DepartmentDTO deleteDepartment(@PathVariable String id);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE, method = POST, value = "/save",
            produces = APPLICATION_JSON_VALUE)
    DepartmentDTO saveDepartment(@RequestBody DepartmentDTO department);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET, value = "/get-department-list-by-URL-params")
    List<DepartmentDTO> getDepartmentListByURLParams(@RequestParam String search);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/get-department-list-by-department-params",
            produces = APPLICATION_JSON_VALUE)
    List<DepartmentDTO> getDepartmentListByDepartmentParams(@RequestBody DepartmentDTO departmentParams);
}