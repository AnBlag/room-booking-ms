package ru.roombooking.admin.feign;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.roombooking.admin.model.EmployeeView;
import ru.roombooking.admin.model.dto.DepartmentDTO;

import java.util.List;

@FeignClient(name = "employeeView", url = "${feign.employee.url}", path = "/employee-view")
public interface EmployeeViewFeignClient {

    @RequestMapping(consumes = APPLICATION_JSON_VALUE, method = GET, value = "/")
    List<EmployeeView> findAll();

    @RequestMapping(consumes = APPLICATION_JSON_VALUE, method = POST, value = "/save",
            produces = APPLICATION_JSON_VALUE)
    DepartmentDTO saveEmployeeView(@RequestBody EmployeeView employeeView);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE, method = PUT, value = "/batch-update-profile-and-employee",
            produces = APPLICATION_JSON_VALUE)
    void batchUpdateProfileAndEmployee(@RequestBody List<EmployeeView> employeeViewList);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = GET,
            value = "/get-employee-view-list-by-URL-params")
    List<EmployeeView> getEmployeeViewListByURLParams(@RequestParam String search);

    @RequestMapping(consumes = APPLICATION_JSON_VALUE,
            method = POST,
            value = "/get-employee-view-list-by-employee-view-params",
            produces = APPLICATION_JSON_VALUE)
    List<EmployeeView> getEmployeeViewListByEmployeeViewParams(@RequestBody EmployeeView employeeViewParams);
}