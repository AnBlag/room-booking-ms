package ru.roombooking.employee.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.roombooking.employee.model.Department;

@FeignClient(name = "department", url = "http://localhost:8084", path = "/department")
public interface DepartmentFeignClient {
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/find-by-id/{id}")
    Department findById(@PathVariable String id);
}
