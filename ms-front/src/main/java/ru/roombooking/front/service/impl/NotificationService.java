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
}