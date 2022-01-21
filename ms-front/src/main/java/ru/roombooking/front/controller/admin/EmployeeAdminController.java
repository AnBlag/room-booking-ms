package ru.roombooking.front.controller.admin;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.front.exception.EditEmployeeBadRequestException;
import ru.roombooking.front.exception.EmployeeBadRequestException;
import ru.roombooking.front.exception.EmployeeViewBadRequestException;
import ru.roombooking.front.exception.SaveEmployeeBadRequestException;
import ru.roombooking.front.feign.admin.EmployeeAdminFeignClient;
import ru.roombooking.front.model.EmployeeView;
import ru.roombooking.front.model.dto.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/employees")
public class EmployeeAdminController {
    private final EmployeeAdminFeignClient employeeAdminFeignClient;

    @PostMapping("/save")
    public String updateUsers(@RequestParam(name = "id") String id,
                              @RequestParam(name = "name") String name,
                              @RequestParam(name = "surname") String surname,
                              @RequestParam(name = "middleName") String middleName,
                              @RequestParam(name = "phone") String phone,
                              @RequestParam(name = "email") String email,
                              @RequestParam(name = "banned") String banned
    ) {
        try {
            employeeAdminFeignClient.updateUsers(new EmployeeRequest(id, name, surname, middleName, phone, email, banned));
        } catch (FeignException e) {
            throw new EmployeeBadRequestException();
        }

        return "redirect:/admin/employees/";
    }

    @GetMapping("/")
    public String employees(@RequestParam(value = "search", required = false) String search,
                            ModelMap modelMap) {
        List<EmployeeView> employeeViewList;
        try {
            employeeViewList = employeeAdminFeignClient.employees(search);
        } catch (FeignException e) {
            throw new EmployeeViewBadRequestException();
        }
        modelMap.addAttribute("employeeList", employeeViewList);
        modelMap.addAttribute("findEmployeeView", new EmployeeView());
        return "adminpagefullfindemployee";
    }

    @PostMapping("/")
    public String findEmployees(@ModelAttribute("findEmployeeView") EmployeeView employeeView,
                                ModelMap modelMap) {
        List<EmployeeView> employeeViewList;
        try {
            employeeViewList = employeeAdminFeignClient.findEmployees(employeeView);
        } catch (FeignException e) {
            throw new EmployeeViewBadRequestException();
        }
        modelMap.addAttribute("employeeList", employeeViewList);
        return "adminpagefullfindemployee";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable String id, ModelMap modelMap) {
        EmployeeEditRequest employeeEditRequest;
        try {
            employeeEditRequest = employeeAdminFeignClient.editEmployee(id);
        } catch (FeignException e) {
            throw new EditEmployeeBadRequestException();
        }
        modelMap.addAttribute("employeeData", employeeEditRequest.getEmployeeDTO());
        modelMap.addAttribute("profileData", employeeEditRequest.getProfile());
        modelMap.addAttribute("departmentData", employeeEditRequest.getDepartmentList());
        return "editemployee";
    }

    @PostMapping("/edit/{id}")
    public String saveEmployee(@PathVariable String id,
                               @ModelAttribute("employeeData")final @Valid EmployeeDTO employeeDTO,
                               @ModelAttribute("profileData")final @Valid ProfileDTO profileDTO) {
        try {
            employeeAdminFeignClient.saveEmployee(new EmployeeSaveRequest(id, employeeDTO, profileDTO));
        } catch (FeignException e) {
            throw new SaveEmployeeBadRequestException();
        }

        return "redirect:/admin/employees/";
    }

    @GetMapping("/delete/{id}")
    public String askDeleteEmployee(@PathVariable String id, ModelMap modelMap) {
        modelMap.addAttribute("profileId", id);
        return "deleteemployee";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable String id) {
        try {
            employeeAdminFeignClient.deleteEmployee(id);
        } catch (FeignException e) {
            throw new EmployeeBadRequestException();
        }

        return "redirect:/admin/employees/";
    }
}
