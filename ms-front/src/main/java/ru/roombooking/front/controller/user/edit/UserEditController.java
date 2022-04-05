package ru.roombooking.front.controller.user.edit;

import feign.FeignException;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.roombooking.front.exception.DepartmentRequestException;
import ru.roombooking.front.feign.DepartmentFeignClient;
import ru.roombooking.front.model.dto.EmployeeDTO;
import ru.roombooking.front.model.dto.ProfileDTO;
import ru.roombooking.front.service.EmployeeAndProfileService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserEditController {
    private final EmployeeAndProfileService employeeAndProfileService;
    private final DepartmentFeignClient departmentFeignClient;

    @GetMapping("/user/edit")
    public String editEmployee(ModelMap modelMap) {

        log.info("Запрос на изменение данных о сотруднике");
        EmployeeDTO employeeDTO = employeeAndProfileService.findByLogin(getUserAuth().getUsername());
        ProfileDTO profile = employeeAndProfileService.findProfileById(employeeDTO.getProfileId());

        modelMap.addAttribute("employeeData", employeeDTO);
        modelMap.addAttribute("profileData", profile);

        try {
            modelMap.addAttribute("departmentData", departmentFeignClient.findAll());
        } catch (FeignException e) {
            throw new DepartmentRequestException();
        }

        return "edituserpage";
    }

    @PostMapping("/user")
    public String saveEmployee(@ModelAttribute("employeeData") final @Valid EmployeeDTO employeeDTO,
                               @ModelAttribute("profileData") final @Valid ProfileDTO profile) {

        log.info("Изменение данных о сотруднике");
        EmployeeDTO tempEmployeeDTO = employeeAndProfileService.findByLogin(profile.getLogin());
        ProfileDTO tempProfile = employeeAndProfileService.findProfileById(tempEmployeeDTO.getProfileId());

        tempEmployeeDTO.setName(employeeDTO.getName());
        tempEmployeeDTO.setSurname(employeeDTO.getSurname());
        tempEmployeeDTO.setMiddleName(employeeDTO.getMiddleName());
        tempEmployeeDTO.setPhone(employeeDTO.getPhone());
        tempEmployeeDTO.setEmail(employeeDTO.getEmail());
        tempEmployeeDTO.setDepartmentId(employeeDTO.getDepartmentId());

        employeeAndProfileService.update(tempEmployeeDTO, tempProfile);
        log.info("Изменение данных о сотруднике успешно завершено");
        return "redirect:/user/edit";
    }

    private User getUserAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}