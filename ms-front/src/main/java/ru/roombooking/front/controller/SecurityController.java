package ru.roombooking.front.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.roombooking.front.service.impl.NotificationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/security")
public class SecurityController {

    private final NotificationService notificationService;

    @GetMapping("/is-admin")
    public ResponseEntity<Boolean> isAdmin(@RequestParam String login) {
        return ResponseEntity.ok(notificationService.isAdmin(login));
    }
}
