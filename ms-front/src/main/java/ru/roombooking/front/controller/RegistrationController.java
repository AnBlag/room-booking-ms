package ru.roombooking.front.controller;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.roombooking.front.exception.DepartmentRequestException;
import ru.roombooking.front.feign.DepartmentFeignClient;
import ru.roombooking.front.feign.RegistrationFeignClient;
import ru.roombooking.front.model.dto.RegistrationDTO;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {
    private final DepartmentFeignClient departmentFeignClient;
    private final RegistrationFeignClient registrationFeignClient;

    @GetMapping("/registration")
    public String registration(Model model) {
        log.info("Создание регистрации");
        model.addAttribute("userData", new RegistrationDTO());

        try {
            model.addAttribute("departmentList", departmentFeignClient.findAll());
        } catch (FeignException e) {
            throw new DepartmentRequestException();
        }

        return "registration";
    }

    @PostMapping("/registration")
    public String userRegistration(@ModelAttribute("userData") final @Valid RegistrationDTO registrationDTO,
                                   final Model model) {
        try {
            model.addAttribute("departmentList", departmentFeignClient.findAll());
        } catch (FeignException e) {
            throw new DepartmentRequestException();
        }

        log.info("Регистрация пользователя");
        try {
            registrationFeignClient.userRegistration(registrationDTO);
            model.addAttribute("loginErrorMessage", "");
            log.info("Регистрация пользователя успешно завершена");
            return "redirect:/";
        } catch (FeignException e) {
            log.info("Ошибка при регистрации пользователя " + e.status());
            if (e.status() == 400) {
                model.addAttribute("loginErrorMessage", "Такой логин уже существует!");
            } else {
                model.addAttribute("loginErrorMessage", "Ошибка регистрации!");
            }
            return "registration";
        }
    }
}