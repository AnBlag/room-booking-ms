package ru.roombooking.departments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.departments.model.Department;
import ru.roombooking.departments.service.impl.NotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {
    private final NotificationService notificationService;

    @GetMapping("/")
    public ResponseEntity<List<Department>> findAll() {
        return ResponseEntity.ok(notificationService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        return ResponseEntity.ok(notificationService.saveDepartment(department));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department, @PathVariable String id) {
        return ResponseEntity.ok(notificationService.updateDepartment(department, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable String id) {
        return ResponseEntity.ok(notificationService.deleteDepartment(id));
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Department> findById(@PathVariable String id) {
        return ResponseEntity.ok(notificationService.findById(id));
    }

    @PutMapping("/batch-update-department")
    public void batchUpdateDepartment(@RequestBody List<Department> departmentList) {
        notificationService.batchUpdateDepartment(departmentList);
    }

    @GetMapping("/get-department-list-by-URL-params")
    public ResponseEntity<List<Department>> getDepartmentListByURLParams(@RequestParam String search) {
        return ResponseEntity.ok(notificationService.getDepartmentListByURLParams(search));
    }

    @PostMapping("/get-department-list-by-department-params")
    public ResponseEntity<List<Department>> getDepartmentListByDepartmentParams(@RequestBody Department departmentParams) {
        return ResponseEntity.ok(notificationService.getDepartmentListByDepartmentParams(departmentParams));
    }
}