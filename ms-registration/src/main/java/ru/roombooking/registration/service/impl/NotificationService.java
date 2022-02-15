package ru.roombooking.registration.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.roombooking.registration.exception.UserRegistrationException;
import ru.roombooking.registration.model.Role;
import ru.roombooking.registration.model.dto.RegistrationDTO;
import ru.roombooking.registration.service.RegistrationService;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final RegistrationService registrationService;
    private final PasswordEncoder passwordEncoder;

    public void userRegistration(RegistrationDTO registrationDTO) {
        log.info("Создание регистрации");
        if (!registrationService.doesUserExist(registrationDTO)) {
            registrationDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
            registrationDTO.setAccountNonLocked(true);
            registrationDTO.setIsActive(true);
            registrationDTO.setRole(Role.EMPLOYEE);

            registrationService.saveEmployeeAndProfile(registrationDTO);
        } else {
            throw new UserRegistrationException();
        }
    }
}