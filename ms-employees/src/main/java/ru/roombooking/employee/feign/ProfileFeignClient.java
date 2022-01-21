package ru.roombooking.employee.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.roombooking.employee.model.Profile;


@FeignClient(name = "profile", url = "http://localhost:8086", path = "/profile")
public interface ProfileFeignClient {

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/find-by-id/{id}")
    Profile findById(@PathVariable String id);

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/find-by-login")
    Profile findByLogin(@RequestParam String login);
}
