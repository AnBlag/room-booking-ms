package ru.roombooking.profile.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.profile.exception.ChangeAccountNonLockedException;
import ru.roombooking.profile.exception.ProfileDeleteException;
import ru.roombooking.profile.exception.ProfileSaveException;
import ru.roombooking.profile.exception.ProfileUpdateException;
import ru.roombooking.profile.model.Profile;
import ru.roombooking.profile.service.ProfileService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final ProfileService profileService;

    @Transactional(readOnly = true)
    public List<Profile> findAll() {
        return profileService.findAll();
    }

    @Transactional(rollbackFor = ProfileSaveException.class)
    public Profile saveProfile(Profile profile) {
        return profileService.save(profile);
    }

    @Transactional(rollbackFor = ProfileUpdateException.class)
    public Profile updateProfile(Profile profile, String id) {
        return profileService.update(profile, Long.parseLong(id));
    }

    @Transactional(rollbackFor = ProfileDeleteException.class)
    public Profile deleteProfile(String id) {
        return profileService.deleteById(Long.parseLong(id));
    }

    @Transactional(rollbackFor = ProfileDeleteException.class)
    public Profile deleteByProfile(Profile profile) {
        return profileService.deleteByProfile(profile);
    }

    @Transactional(rollbackFor = ChangeAccountNonLockedException.class)
    public Profile tempBanned(String id, String status) {
        return profileService.changeAccountNonLocked(Boolean.parseBoolean(status), Long.parseLong(id));
    }

    @Transactional(readOnly = true)
    public Profile findByLogin(String login) {
        return profileService.findByLogin(login);
    }

    @Transactional(readOnly = true)
    public Profile findByID(String id) {
        return profileService.findById(Long.parseLong(id));
    }

    @Transactional(readOnly = true)
    public Boolean doesProfileExist(String login) {
        return profileService.doesProfileExist(login);
    }
}