package ru.roombooking.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.roombooking.admin.model.EmployeeView;
import ru.roombooking.admin.model.dto.DepartmentDTO;

import java.util.List;

@FeignClient(name = "employeeView", url = "${feign.employee.url}", path = "/employee-view")
public interface EmployeeViewFeignClient {

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/")
    List<EmployeeView> findAll();

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, value = "/save",
            produces = MediaType.APPLICATION_JSON_VALUE)
    DepartmentDTO saveEmployeeView(@RequestBody EmployeeView employeeView);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT, value = "/batch-update-profile-and-employee",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void batchUpdateProfileAndEmployee(@RequestBody List<EmployeeView> employeeViewList);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET,
            value = "/get-employee-view-list-by-URL-params")
    List<EmployeeView> getEmployeeViewListByURLParams(@RequestParam String search);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            value = "/get-employee-view-list-by-employee-view-params",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<EmployeeView> getEmployeeViewListByEmployeeViewParams(@RequestBody EmployeeView employeeViewParams);
}