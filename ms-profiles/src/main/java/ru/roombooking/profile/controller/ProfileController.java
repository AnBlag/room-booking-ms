package ru.roombooking.profile.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.profile.model.Profile;
import ru.roombooking.profile.service.impl.NotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final NotificationService notificationService;

    @GetMapping("/")
    public ResponseEntity<List<Profile>> findAll() {
        return ResponseEntity.ok(notificationService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<Profile> saveProfile(@RequestBody Profile profile) {
        return ResponseEntity.ok(notificationService.saveProfile(profile));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile profile, @PathVariable String id) {
        return ResponseEntity.ok(notificationService.updateProfile(profile, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Profile> deleteProfile(@PathVariable String id) {
        return ResponseEntity.ok(notificationService.deleteProfile(id));
    }

    @DeleteMapping("/delete-by-profile")
    public ResponseEntity<Profile> deleteByProfile(@RequestBody Profile profile) {
        return ResponseEntity.ok(notificationService.deleteByProfile(profile));
    }

    @PutMapping("/temp-banned")
    public ResponseEntity<Profile> tempBanned(@RequestParam String id, @RequestParam String status) {
        return ResponseEntity.ok(notificationService.tempBanned(status, id));
    }

    @GetMapping("/find-by-login")
    public ResponseEntity<Profile> findByLogin(@RequestParam String login) {
        return ResponseEntity.ok(notificationService.findByLogin(login));
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Profile> findById(@PathVariable String id) {
        return ResponseEntity.ok(notificationService.findByID(id));
    }

    @GetMapping("/does-profile-exist")
    public ResponseEntity<Boolean> doesProfileExist(@RequestParam String login) {
        return ResponseEntity.ok(notificationService.doesProfileExist(login));
    }
}