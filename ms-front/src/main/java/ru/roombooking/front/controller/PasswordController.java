package ru.roombooking.front.controller;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.roombooking.front.exception.SetNewPasswordException;
import ru.roombooking.front.feign.PasswordFeignClient;
import ru.roombooking.front.model.dto.ProfileDTO;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PasswordController {
    private final PasswordFeignClient passwordFeignClient;

    @GetMapping("/forget-password")
    public String forgetPassword() {
        log.info("Запрос на смену пароля");
        return "forgetpassword";
    }

    @PostMapping("/forget-password/send")
    public String forgetPassword(@RequestParam(value = "username") String username, ModelMap modelMap) {

        log.info("Отправка письма пользователю для смены пароля");
        try {
            passwordFeignClient.forgetPassword(username);
            log.info("Письмо успешно отправлено");
            return "succesfulSendEmailForgetPassword";
        } catch (FeignException e) {
            modelMap.addAttribute("error", true);
            log.info("Ошибка отправки письма");
            return "forgetpassword";
        }
    }

    @GetMapping("/reset-password")
    public String resetPassword(@RequestParam("token") String confirmationToken, ModelMap modelMap) {
        log.info("Сброс пароля");
        try {
            modelMap.addAttribute("profileData", passwordFeignClient.resetPassword(confirmationToken));
            log.info("Токен принят успешно");
        } catch (FeignException e) {
            modelMap.addAttribute("error", true);
            log.info("Ошибка токена");
        }
        return "resetpassword";
    }

    @PostMapping("reset-password")
    public String saveNewPassword(@ModelAttribute("profileData") ProfileDTO newProfileData) {
        log.info("Сохранение нового пароля");
        try {
            passwordFeignClient.saveNewPassword(newProfileData);
            log.info("Сохранение нового пароля успешно завершено");
            return "succesfulSendEmailForgetPassword";
        } catch (FeignException e) {
            throw new SetNewPasswordException();
        }
    }
}