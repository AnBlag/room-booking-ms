package ru.roombooking.employee.feign;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.roombooking.employee.model.dto.DepartmentDTO;

@FeignClient(name = "department", url = "${feign.department.url}", path = "/department")
public interface DepartmentFeignClient {
    @RequestMapping(consumes = APPLICATION_JSON_VALUE, method = GET, value = "/find-by-id/{id}")
    DepartmentDTO findById(@PathVariable String id);
}