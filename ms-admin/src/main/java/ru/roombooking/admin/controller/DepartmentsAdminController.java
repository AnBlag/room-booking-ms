package ru.roombooking.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.admin.model.dto.DepartmentDTO;
import ru.roombooking.admin.model.dto.DepartmentRequest;
import ru.roombooking.admin.service.notification.DepartmentNotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/departments")
public class DepartmentsAdminController {
    private final DepartmentNotificationService departmentNotificationService;

    @GetMapping("/")
    public ResponseEntity<List<DepartmentDTO>> departments(@RequestParam(required = false) String search) {
        return ResponseEntity.ok(departmentNotificationService.departments(search));
    }

    @PostMapping("/")
    public ResponseEntity<List<DepartmentDTO>> findDepartments(@RequestBody DepartmentDTO findDepartment) {
        return ResponseEntity.ok(departmentNotificationService.findDepartments(findDepartment));
    }

    @PostMapping("/save")
    public void updateDepartments(@RequestBody DepartmentRequest departmentRequest) {
        departmentNotificationService.updateDepartments(departmentRequest);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> askDeleteDepartment(@PathVariable String id) {
        return ResponseEntity.ok(departmentNotificationService.askToDeleteDepartment(id));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDepartment(@PathVariable String id) {
        departmentNotificationService.deleteDepartment(id);
    }

    @PostMapping("/add")
    public void saveNewDepartment(@RequestBody DepartmentDTO department) {
        departmentNotificationService.saveNewDepartment(department);
    }
}