package ru.roombooking.front.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.roombooking.front.model.dto.DepartmentDTO;

import java.util.List;

@FeignClient(name = "department", url = "http://localhost:8084", path = "/department")
public interface DepartmentFeignClient {
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/")
    List<DepartmentDTO> findAll();
}
