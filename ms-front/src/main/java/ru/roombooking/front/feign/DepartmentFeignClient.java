package ru.roombooking.front.feign;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.roombooking.front.model.dto.DepartmentDTO;

import java.util.List;

@FeignClient(name = "department", url = "${feign.department.url}", path = "/department")
public interface DepartmentFeignClient {
    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/")
    List<DepartmentDTO> findAll();
}