package ru.roombooking.front.controller;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.roombooking.front.exception.EmployeeNotFoundException;
import ru.roombooking.front.feign.GetUserFeignClient;
import ru.roombooking.front.model.dto.EmployeeDTO;

@RestController
@AllArgsConstructor
@Slf4j
public class GetUserController {
    private final GetUserFeignClient getUserFeignClient;

    @GetMapping("/get-user")
    public ResponseEntity<EmployeeDTO> getEmployee() {

        log.info("Получение данных пользователя по логину");
        try {
            return ResponseEntity.ok(getUserFeignClient.getUser(getUserAuth().getUsername()));
        } catch (FeignException e) {
            throw new EmployeeNotFoundException();
        }
    }

    private User getUserAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}