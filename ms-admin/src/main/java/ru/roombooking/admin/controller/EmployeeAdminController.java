package ru.roombooking.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.admin.model.EmployeeView;
import ru.roombooking.admin.model.dto.EmployeeEditRequest;
import ru.roombooking.admin.model.dto.EmployeeRequest;
import ru.roombooking.admin.model.dto.EmployeeSaveRequest;
import ru.roombooking.admin.service.notification.EmployeeNotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/employees")
public class EmployeeAdminController {
    private final EmployeeNotificationService employeeNotificationService;

    @PostMapping("/save")
    public void updateUsers(@RequestBody EmployeeRequest employeeRequest) {
        employeeNotificationService.updateUsers(employeeRequest);
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeView>> employees(@RequestParam(required = false) String search) {
        return ResponseEntity.ok(employeeNotificationService.getByParamPage(search));
    }

    @PostMapping("/")
    public ResponseEntity<List<EmployeeView>> findEmployees(@RequestBody EmployeeView employeeView) {
        return ResponseEntity.ok(employeeNotificationService.findByParamPage(employeeView));
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<EmployeeEditRequest> editEmployee(@PathVariable String id) {
        return ResponseEntity.ok(employeeNotificationService.editEmployee(id));
    }

    @PostMapping("/edit/")
    public void saveEmployee(@RequestBody EmployeeSaveRequest employeeSaveRequest) {
        employeeNotificationService.saveEmployee(employeeSaveRequest.getId(),
                employeeSaveRequest.getEmployeeDTO(),
                employeeSaveRequest.getProfile());
    }

    /*@GetMapping("/delete/{id}")
    public String askDeleteEmployee(@PathVariable String id, ModelMap modelMap) {
        modelMap.addAttribute("profileId", id);
        return "deleteemployee";
    }*/

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable String id) {
        employeeNotificationService.deleteEmployee(id);
    }
}
