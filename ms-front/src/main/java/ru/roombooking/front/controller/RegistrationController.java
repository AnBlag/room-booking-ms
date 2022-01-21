package ru.roombooking.front.controller;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ru.roombooking.front.exception.DepartmentBadRequestException;
import ru.roombooking.front.feign.DepartmentFeignClient;
import ru.roombooking.front.feign.RegistrationFeignClient;
import ru.roombooking.front.model.dto.RegistrationDTO;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {

    //private final DepartmentService departmentService;
    private final DepartmentFeignClient departmentFeignClient;
    private final RegistrationFeignClient registrationFeignClient;
    private final PasswordEncoder passwordEncoder;



    @GetMapping("/registration")
    public String registration(Model model) {
        log.info("Создание регистрации");
        model.addAttribute("userData", new RegistrationDTO());


        try {
            model.addAttribute("departmentList", departmentFeignClient.findAll());
        } catch (FeignException e) {
            throw new DepartmentBadRequestException();
        }

        //model.addAttribute("departamentService", departmentService);
        return "registration";
    }

    @PostMapping("/registration")
    public String userRegistration(@ModelAttribute("userData")final @Valid RegistrationDTO registrationDTO, final BindingResult bindingResult, final Model model) {

        try {
            model.addAttribute("departmentList", departmentFeignClient.findAll());
        } catch (FeignException e) {
            throw new DepartmentBadRequestException();
        }

        //model.addAttribute("departamentService", departmentService);

        //SuccessResponse successResponse = registrationFeignClient.userRegistration(registrationDTO);

        try {
            registrationFeignClient.userRegistration(registrationDTO);
            model.addAttribute("loginErrorMessage", false);
            return "redirect:/";
        } catch (FeignException e){
            model.addAttribute("loginErrorMessage", true);
            return "registration";
        }
    }

}
