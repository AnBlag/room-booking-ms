package ru.roombooking.front.controller.admin;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.front.exception.EditEmployeeRequestException;
import ru.roombooking.front.exception.EmployeeRequestException;
import ru.roombooking.front.exception.EmployeeViewRequestException;
import ru.roombooking.front.exception.SaveEmployeeRequestException;
import ru.roombooking.front.feign.admin.EmployeeAdminFeignClient;
import ru.roombooking.front.model.EmployeeView;
import ru.roombooking.front.model.dto.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/employees")
public class EmployeeAdminController {
    private final EmployeeAdminFeignClient employeeAdminFeignClient;

    @PostMapping("/save")
    public String updateUsers(@RequestParam(name = "id") String id,
                              @RequestParam(name = "surname") String surname,
                              @RequestParam(name = "name") String name,
                              @RequestParam(name = "middleName") String middleName,
                              @RequestParam(name = "phone") String phone,
                              @RequestParam(name = "email") String email,
                              @RequestParam(name = "banned") String banned
    ) {
        log.info("Обновление сотрудников");
        try {
            employeeAdminFeignClient.updateUsers(new EmployeeRequest(id, surname, name, middleName, phone, email, banned));
        } catch (FeignException e) {
            throw new EmployeeRequestException();
        }
        log.info("Обновление сотрудников успешно завершено");

        return "redirect:/admin/employees/";
    }

    @GetMapping("/")
    public String employees(@RequestParam(value = "search", required = false) String search,
                            ModelMap modelMap) {
        log.info("Поиск сотрудников");
        List<EmployeeView> employeeViewList;
        try {
            employeeViewList = employeeAdminFeignClient.employees(search);
        } catch (FeignException e) {
            throw new EmployeeViewRequestException();
        }
        modelMap.addAttribute("employeeList", employeeViewList);
        modelMap.addAttribute("findEmployeeView", new EmployeeView());
        return "adminpagefullfindemployee";
    }

    @PostMapping("/")
    public String findEmployees(@ModelAttribute("findEmployeeView") EmployeeView employeeView,
                                ModelMap modelMap) {
        log.info("Поиск сотрудников по параметрам");
        List<EmployeeView> employeeViewList;
        try {
            employeeViewList = employeeAdminFeignClient.findEmployees(employeeView);
        } catch (FeignException e) {
            throw new EmployeeViewRequestException();
        }
        modelMap.addAttribute("employeeList", employeeViewList);
        return "adminpagefullfindemployee";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable String id, ModelMap modelMap) {

        log.info("Запрос на обновление данных о сотруднике");
        EmployeeEditDTO employeeEditDTO;
        try {
            employeeEditDTO = employeeAdminFeignClient.editEmployee(id);
        } catch (FeignException e) {
            throw new EditEmployeeRequestException();
        }
        modelMap.addAttribute("employeeData", employeeEditDTO.getEmployeeDTO());
        modelMap.addAttribute("profileData", employeeEditDTO.getProfile());
        modelMap.addAttribute("departmentData", employeeEditDTO.getDepartmentList());
        log.info("Обновление данных о сотруднике успешно завершено");
        return "editemployee";
    }

    @PostMapping("/edit/{id}")
    public String saveEmployee(@PathVariable String id,
                               @ModelAttribute("employeeData") final @Valid EmployeeDTO employeeDTO,
                               @ModelAttribute("profileData") final @Valid ProfileDTO profileDTO) {

        log.info("Обновление данных о сотруднике");
        try {
            employeeAdminFeignClient.saveEmployee(new EmployeeSaveDTO(id, employeeDTO, profileDTO));
        } catch (FeignException e) {
            throw new SaveEmployeeRequestException();
        }
        log.info("Обновление данных о сотруднике успешно завершено");

        return "redirect:/admin/employees/";
    }

    @GetMapping("/delete/{id}")
    public String askToDeleteEmployee(@PathVariable String id, ModelMap modelMap) {
        log.info("Запрос на удаление сотрудника");
        modelMap.addAttribute("profileId", id);
        return "deleteemployee";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable String id) {
        log.info("Удаление сотрудника");
        try {
            employeeAdminFeignClient.deleteEmployee(id);
        } catch (FeignException e) {
            throw new EmployeeRequestException();
        }
        log.info("Удаление сотрудника успешно завершено");

        return "redirect:/admin/employees/";
    }
}