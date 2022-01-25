package ru.roombooking.front.controller;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
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

public class PasswordController {
    private final PasswordFeignClient passwordFeignClient;

    @GetMapping("/forget-password")
    public String forgetPassword() {
        return "forgetpassword";
    }

    @PostMapping("/forget-password/send")
    public String forgetPassword(@RequestParam(value = "username") String username, ModelMap modelMap) {

        try {
            passwordFeignClient.forgetPassword(username);
            return "succesfulSendEmailForgetPassword";
        } catch (FeignException e) {
            modelMap.addAttribute("error", true);
            return "forgetpassword";
        }
    }

    @GetMapping("/reset-password")
    public String resetPassword(@RequestParam("token") String confirmationToken, ModelMap modelMap) {
        try {
            modelMap.addAttribute("profileData", passwordFeignClient.resetPassword(confirmationToken));

        } catch (FeignException e) {
            modelMap.addAttribute("error", true);
        }
        return "resetpassword";
    }

    @PostMapping("reset-password")
    public String saveNewPassword(@ModelAttribute("profileData") ProfileDTO newProfileData) {

        try {
            passwordFeignClient.saveNewPassword(newProfileData);
            return "succesfulSendEmailForgetPassword";
        } catch (FeignException e) {
            throw new SetNewPasswordException();
        }
    }
}