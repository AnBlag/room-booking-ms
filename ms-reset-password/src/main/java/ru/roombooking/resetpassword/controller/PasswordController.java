package ru.roombooking.resetpassword.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.resetpassword.model.Profile;
import ru.roombooking.resetpassword.service.impl.NotificationService;


@RestController
@RequiredArgsConstructor
//@PropertySource("classpath:password-text.properties")
public class PasswordController {
/*    @Value("${reset.url}")
    private String resetPasswordUrl;
    private final PasswordConfirmationTokenService passwordConfirmationTokenService;
    private final PasswordEncoder passwordEncoder;*/
    private final NotificationService notificationService;


    /*
    @GetMapping("/forget-password")
    public String forgetPassword() {
        return "forgetpassword";
    }
    */

    @PostMapping("/forget-password/send/{username}")
    public void forgetPassword(@PathVariable String username) {

        notificationService.forgetPassword(username);

        /*try {
            Profile profile = profileService.findByLogin(username);
            String email = employeeService.findByProfileID(profile.getId()).getEmail();

            mailSenderService.send(email, "Forget password", "You forgot password" +
                    " link: " + resetPasswordUrl + saveToken(profile).getToken());
            return "succesfulSendEmailForgetPassword";
        }
        catch (ProfileNotFoundException e){
            modelMap.addAttribute("error", true);
            return "forgetpassword";
        }*/

    }

    @GetMapping("/reset-password")
    public ResponseEntity<Profile> resetPassword(@RequestParam String confirmationToken) {


        return ResponseEntity.ok(notificationService.resetPassword(confirmationToken));


        /*try {
            PasswordConfirmationToken passwordConfirmationToken = passwordConfirmationTokenService.findByToken(confirmationToken);
            Profile profile = profileService.findById(passwordConfirmationToken.getProfileId().getId());
            modelMap.addAttribute("profileData", profile);
            passwordConfirmationTokenService.deleteById(passwordConfirmationToken.getId());
        }
        catch (NoSuchElementException e){
            modelMap.addAttribute("error", true);
        }
        return "resetpassword";*/
    }

    @PostMapping("reset-password")
    public void saveNewPassword(@RequestBody Profile newProfileData) {

        notificationService.saveNewPassword(newProfileData);
        /*Profile profile = profileService.findByLogin(newProfileData.getLogin());
        profile.setPassword(passwordEncoder.encode(newProfileData.getPassword()));
        profileService.save(profile);
        return "succesfulSendEmailForgetPassword";*/
    }
}
