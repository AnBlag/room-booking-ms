package ru.roombooking.registration.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.registration.exception.UserRegistrationException;
import ru.roombooking.registration.model.Role;
import ru.roombooking.registration.model.dto.RegistrationDTO;
import ru.roombooking.registration.service.RegistrationService;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final RegistrationService registrationService;

    @Transactional(rollbackFor = UserRegistrationException.class)
    public void userRegistration(RegistrationDTO registrationDTO) {
        if (!registrationService.doesUserExist(registrationDTO)) {
            registrationDTO.setPassword(passwordEncoder().encode(registrationDTO.getPassword()));
            registrationDTO.setAccountNonLocked(true);
            registrationDTO.setIsActive(true);
            registrationDTO.setRole(Role.EMPLOYEE);

            registrationService.saveEmployeeAndProfile(registrationDTO);
        } else {
            throw new UserRegistrationException("Такой логин уже существует!");
        }
    }

    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y, 12);
    }
}