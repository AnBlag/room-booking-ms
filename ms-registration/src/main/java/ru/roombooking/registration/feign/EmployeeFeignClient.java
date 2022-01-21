package ru.roombooking.registration.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.roombooking.registration.model.dto.EmployeeDTO;



@FeignClient(name = "employee", url = "http://localhost:8085", path = "/employee")
public interface EmployeeFeignClient {

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET,
                    value ="/find-by-profile/{profileId}")
    EmployeeDTO findByProfileID(@PathVariable String profileId);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST,
                    value = "/save",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO);
}
