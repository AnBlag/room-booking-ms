package ru.roombooking.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.admin.model.Department;
import ru.roombooking.admin.model.dto.DepartmentRequest;
import ru.roombooking.admin.service.notification.DepartmentNotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/departments")
public class DepartmentsAdminController {
    private final DepartmentNotificationService departmentNotificationService;

    @GetMapping("/")
    public ResponseEntity<List<Department>> departments(@RequestParam(required = false) String search) {
        return ResponseEntity.ok(departmentNotificationService.departments(search));
    }

    @PostMapping("/")
    public ResponseEntity<List<Department>> findDepartments(@RequestBody Department findDepartment) {
        return ResponseEntity.ok(departmentNotificationService.findDepartments(findDepartment));
    }

    @PostMapping("/save")
    public void updateDepartments(@RequestBody DepartmentRequest departmentRequest) {
        departmentNotificationService.updateDepartments(departmentRequest);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> askDeleteDepartment(@PathVariable String id) {
        return ResponseEntity.ok(departmentNotificationService.askDeleteDepartment(id));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDepartment(@PathVariable String id) {
        departmentNotificationService.deleteDepartment(id);
    }

    /*@GetMapping("/add")
    public String addDepartment(ModelMap modelMap) {
        modelMap.addAttribute("departmentData", new Department());
        return "addingdepartment";
    }*/

    @PostMapping("/add")
    public void saveNewDepartment(@RequestBody Department department) {
        departmentNotificationService.saveNewDepartment(department);
    }
}
