package ru.roombooking.employee.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.roombooking.employee.model.dto.EmployeeDTO;
import ru.roombooking.employee.service.impl.NotificationService;

@RestController
@AllArgsConstructor
public class GetUserController {
    private final NotificationService notificationService;

    @GetMapping("/get-user")
    public ResponseEntity<EmployeeDTO> getEmployee(@RequestParam String login) {
        return ResponseEntity.ok(notificationService.getEmployee(login));
    }
}