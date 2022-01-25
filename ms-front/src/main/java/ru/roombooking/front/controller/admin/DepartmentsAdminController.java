package ru.roombooking.front.controller.admin;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.front.exception.DepartmentBadRequestException;
import ru.roombooking.front.feign.admin.DepartmentAdminFeignClient;
import ru.roombooking.front.model.dto.DepartmentDTO;
import ru.roombooking.front.model.dto.DepartmentRequest;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/departments")
public class DepartmentsAdminController {
    private final DepartmentAdminFeignClient departmentAdminFeignClient;

    @GetMapping("/")
    public String departments(@RequestParam(value = "search", required = false) String search,
                              ModelMap modelMap) {
        List<DepartmentDTO> departmentList;
        try {
            departmentList = departmentAdminFeignClient.departments(search);
        } catch (FeignException e) {
            throw new DepartmentBadRequestException();
        }
        modelMap.addAttribute("departmentList", departmentList);
        modelMap.addAttribute("findDepartment", new DepartmentDTO());
        return "departmentadminpage";
    }

    @PostMapping("/")
    public String findDepartments(@ModelAttribute("findDepartment") DepartmentDTO findDepartment,
                                  ModelMap modelMap) {
        List<DepartmentDTO> departmentList;
        try {
            departmentList = departmentAdminFeignClient.findDepartments(findDepartment);
        } catch (FeignException e) {
            throw new DepartmentBadRequestException();
        }

        modelMap.addAttribute("departmentList", departmentList);
        return "departmentadminpage";
    }

    @PostMapping("/save")
    public String updateDepartments(@RequestParam(name = "id") String id,
                                    @RequestParam(name = "departmentName") String departmentName,
                                    @RequestParam(name = "position") String position) {
        try {
            departmentAdminFeignClient.updateDepartments(new DepartmentRequest(id, departmentName, position));
        } catch (FeignException e) {
            throw new DepartmentBadRequestException();
        }

        return "redirect:/admin/departments/";
    }

    @GetMapping("/delete/{id}")
    public String askDeleteDepartment(@PathVariable String id, ModelMap modelMap) {
        String message;
        try {
            message = departmentAdminFeignClient.askDeleteDepartment(id);
        } catch (FeignException e) {
            throw new DepartmentBadRequestException();
        }
        modelMap.addAttribute("departmentId", id);
        modelMap.addAttribute("message", message);
        return "deletedepartment";
    }

    @PostMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable String id) {
        try {
            departmentAdminFeignClient.deleteDepartment(id);
        } catch (FeignException e) {
            throw new DepartmentBadRequestException();
        }
        return "redirect:/admin/departments/";
    }

    @GetMapping("/add")
    public String addDepartment(ModelMap modelMap) {
        modelMap.addAttribute("departmentData", new DepartmentDTO());
        return "addingdepartment";
    }

    @PostMapping("/add")
    public String saveNewDepartment(@ModelAttribute("departmentData") final @Valid DepartmentDTO department) {
        try {
            departmentAdminFeignClient.saveNewDepartment(department);
        } catch (FeignException e) {
            throw new DepartmentBadRequestException();
        }
        return "redirect:/admin/departments/";
    }
}