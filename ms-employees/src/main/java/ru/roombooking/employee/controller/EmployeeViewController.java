package ru.roombooking.employee.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.employee.model.EmployeeView;
import ru.roombooking.employee.service.impl.EmployeeViewServiceImpl;
import ru.roombooking.employee.service.impl.NotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee-view")
public class EmployeeViewController {
    private final EmployeeViewServiceImpl profileViewService;
    private final NotificationService notificationService;

    @GetMapping("/")
    public ResponseEntity<List<EmployeeView>> findAll() {
        return ResponseEntity.ok(profileViewService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<EmployeeView> saveEmployeeView(@RequestBody EmployeeView employeeView) {
        return ResponseEntity.ok(profileViewService.save(employeeView));
    }

    @PutMapping("/batch-update-profile-and-employee")
    public void batchUpdateProfileAndEmployee(@RequestBody List<EmployeeView> employeeViewList) {
        profileViewService.batchUpdateProfileAndEmployee(employeeViewList);
    }

    @GetMapping("/get-employee-view-list-by-URL-params")
    public  ResponseEntity<List<EmployeeView>> getEmployeeViewListByURLParams(@RequestParam String search) {
        return ResponseEntity.ok(notificationService.getEmployeeViewListByURLParams(search));
    }

    @PostMapping("/get-employee-view-list-by-employee-view-params")
    public  ResponseEntity<List<EmployeeView>> getEmployeeViewListByEmployeeViewParams(@RequestBody EmployeeView employeeViewParams) {
        return ResponseEntity.ok(notificationService.getEmployeeViewListByEmployeeViewParams(employeeViewParams));
    }

}
