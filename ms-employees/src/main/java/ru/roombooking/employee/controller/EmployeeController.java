package ru.roombooking.employee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.employee.model.dto.ProfileDTO;
import ru.roombooking.employee.model.dto.EmployeeDTO;
import ru.roombooking.employee.service.impl.NotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final NotificationService notificationService;

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> findAll() {
        return ResponseEntity.ok(notificationService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(notificationService.saveEmployee(employeeDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable String id) {
        return ResponseEntity.ok(notificationService.updateEmployee(employeeDTO, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable String id) {
        return ResponseEntity.ok(notificationService.deleteEmployee(id));
    }

    @GetMapping("/find-by-profile/{profileId}")
    public ResponseEntity<EmployeeDTO> findByProfileID(@PathVariable String profileId) {
        return ResponseEntity.ok(notificationService.findByProfileID(profileId));
    }

    @GetMapping("/find-by-login")
    public ResponseEntity<EmployeeDTO> findByLogin(@RequestParam String login) {
        return ResponseEntity.ok(notificationService.findByLogin(login));
    }

    @GetMapping("/get-profile-by-id/{id}")
    public ResponseEntity<ProfileDTO> getProfileById(@PathVariable String id) {
        return ResponseEntity.ok(notificationService.getProfileById(id));
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(notificationService.findById(id));
    }

    @GetMapping("/is-present-by-department-id/{departmentId}")
    public ResponseEntity<Boolean> isPresentByDepartmentId(@PathVariable String departmentId) {
        return ResponseEntity.ok(notificationService.isPresentByDepartmentId(departmentId));
    }
}
