package ru.roombooking.registration.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.roombooking.registration.model.dto.RegistrationDTO;
import ru.roombooking.registration.service.impl.NotificationService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {
    private final NotificationService notificationService;

    @PostMapping("/registration")
    public void userRegistration(@RequestBody RegistrationDTO registrationDTO) {

        notificationService.userRegistration(registrationDTO);
    }
}