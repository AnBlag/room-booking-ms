package ru.roombooking.front.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.roombooking.front.config.security.Role;
import ru.roombooking.front.config.security.auth.UserService;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UserService userService;

    public Boolean isAdmin(String login) {
        return userService.loadUserByUsername(login).getAuthorities().equals(Role.ADMIN.getAuthorities());
    }

    /*public LoginSuccessResponse getUserAuth () {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return new LoginSuccessResponse(0L, user.getUsername());
    }*/

    /*private User getUserAuth(String login) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  (User) authentication.getPrincipal();

        userService.loadUserByUsername(login).

    }*/

}
