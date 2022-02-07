package ru.roombooking.resetpassword.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.resetpassword.exception.EmployeeNotFoundException;
import ru.roombooking.resetpassword.exception.ProfileNotFoundException;
import ru.roombooking.resetpassword.exception.SetNewPasswordException;
import ru.roombooking.resetpassword.feign.EmployeeFeignClient;
import ru.roombooking.resetpassword.feign.MailFeignClient;
import ru.roombooking.resetpassword.feign.ProfileFeignClient;
import ru.roombooking.resetpassword.model.PasswordConfirmationToken;
import ru.roombooking.resetpassword.model.dto.ProfileDTO;
import ru.roombooking.resetpassword.model.dto.EmployeeDTO;
import ru.roombooking.resetpassword.model.dto.MailParams;
import ru.roombooking.resetpassword.service.PasswordConfirmationTokenService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:password-text.properties")
@Slf4j
public class NotificationService {
    @Value("${reset.url}")
    private String resetPasswordUrl;
    private final PasswordConfirmationTokenService passwordConfirmationTokenService;
    private final ProfileFeignClient profileFeignClient;
    private final MailFeignClient mailFeignClient;
    private final EmployeeFeignClient employeeFeignClient;

    public void forgetPassword(String username) {
        log.info("Запрос на восстановление пароля");
        try {
            log.info("Поиск профиля по логину");
            ProfileDTO profile = profileFeignClient.findByLogin(username);
            try {
                log.info("Отправка сообщения для смены пароля");
                EmployeeDTO employeeDTO = employeeFeignClient.findByProfileID(String.valueOf(profile.getId()));
                String email = employeeDTO.getEmail();
                mailFeignClient.send(new MailParams(email, "Forget password", "You forgot password" +
                        " link: " + resetPasswordUrl + saveToken(profile).getToken()));
            } catch (FeignException e) {
                throw new EmployeeNotFoundException();
            }
        } catch (FeignException e) {
            throw new ProfileNotFoundException();
        }
        log.info("Сообщение пользоваателю успешно отправлено");
    }

    @Transactional(readOnly = true)
    public ProfileDTO resetPassword(String confirmationToken) {
        log.info("Проверка токена для сброса пароля");
        PasswordConfirmationToken passwordConfirmationToken = passwordConfirmationTokenService.findByToken(confirmationToken);

        try {
            log.info("Получение данных профиля");
            ProfileDTO profile = profileFeignClient
                    .findById(String.valueOf(passwordConfirmationToken.getProfileId()));
            passwordConfirmationTokenService.deleteById(passwordConfirmationToken.getId());
            log.info("Данные профиля получены успешно");
            return profile;
        } catch (FeignException e) {
            throw new ProfileNotFoundException();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveNewPassword(ProfileDTO newProfileData) {
        log.info("Сохранение нового пароля");
        try {
            ProfileDTO profile = profileFeignClient.findByLogin(newProfileData.getLogin());
            profile.setPassword(passwordEncoder().encode(newProfileData.getPassword()));
            profileFeignClient.saveProfile(profile);
        } catch (FeignException e) {
            throw new SetNewPasswordException();
        }
        log.info("Пароль успешно обновлен");
    }

    private PasswordConfirmationToken saveToken(ProfileDTO profile) {
        PasswordConfirmationToken passwordConfirmationToken = PasswordConfirmationToken.builder()
                .profileId(profile.getId())
                .token(UUID.randomUUID().toString())
                .build();
        return passwordConfirmationTokenService.save(passwordConfirmationToken);
    }

    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y, 12);
    }
}