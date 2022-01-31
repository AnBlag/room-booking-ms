package ru.roombooking.resetpassword.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.resetpassword.model.dto.ProfileDTO;
import ru.roombooking.resetpassword.service.impl.NotificationService;

@RestController
@RequiredArgsConstructor
public class PasswordController {
    private final NotificationService notificationService;

    @PostMapping("/forget-password/send/{username}")
    public void forgetPassword(@PathVariable String username) {
        notificationService.forgetPassword(username);
    }

    @GetMapping("/reset-password")
    public ResponseEntity<ProfileDTO> resetPassword(@RequestParam String confirmationToken) {
        return ResponseEntity.ok(notificationService.resetPassword(confirmationToken));
    }

    @PostMapping("reset-password")
    public void saveNewPassword(@RequestBody ProfileDTO newProfileData) {
        notificationService.saveNewPassword(newProfileData);
    }
}