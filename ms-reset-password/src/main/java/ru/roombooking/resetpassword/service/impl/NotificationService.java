package ru.roombooking.resetpassword.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
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
import ru.roombooking.resetpassword.model.Profile;
import ru.roombooking.resetpassword.model.dto.EmployeeDTO;
import ru.roombooking.resetpassword.model.dto.MailRequest;
import ru.roombooking.resetpassword.service.PasswordConfirmationTokenService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:password-text.properties")
public class NotificationService {

    @Value("${reset.url}")
    private String resetPasswordUrl;
    private final PasswordConfirmationTokenService passwordConfirmationTokenService;
    //private final PasswordEncoder passwordEncoder;
    private final ProfileFeignClient profileFeignClient;
    private final MailFeignClient mailFeignClient;
    private final EmployeeFeignClient employeeFeignClient;


    public void forgetPassword(String username) {


        try {
            Profile profile = profileFeignClient.findByLogin(username);
            try {
                EmployeeDTO employeeDTO = employeeFeignClient.findByProfileID(String.valueOf(profile.getId()));
                String email = employeeDTO.getEmail();
                mailFeignClient.send(new MailRequest(email, "Forget password", "You forgot password" +
                        " link: " + resetPasswordUrl + saveToken(profile).getToken()));
            } catch (FeignException e) {
                throw new EmployeeNotFoundException();
            }
        } catch (FeignException e) {
            throw new ProfileNotFoundException();
        }

    }

    @Transactional(readOnly = true)
    public Profile resetPassword(String confirmationToken) {

        PasswordConfirmationToken passwordConfirmationToken = passwordConfirmationTokenService.findByToken(confirmationToken);

        try {
            Profile profile = profileFeignClient
                    .findById(String.valueOf(passwordConfirmationToken.getProfileId().getId()));
            passwordConfirmationTokenService.deleteById(passwordConfirmationToken.getId());
            return profile;
        } catch (FeignException e){
            throw new ProfileNotFoundException();
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void saveNewPassword(Profile newProfileData) {

        try {
            Profile profile = profileFeignClient.findByLogin(newProfileData.getLogin());
            profile.setPassword(passwordEncoder().encode(newProfileData.getPassword()));
            profileFeignClient.saveProfile(profile);
        } catch (FeignException e) {
            throw new SetNewPasswordException();
        }

    }


    private PasswordConfirmationToken saveToken (Profile profile) {
        PasswordConfirmationToken passwordConfirmationToken = PasswordConfirmationToken.builder()
                .profileId(profile)
                .token(UUID.randomUUID().toString())
                .build();
        return passwordConfirmationTokenService.save(passwordConfirmationToken);
    }

    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y,12);
    }



}
