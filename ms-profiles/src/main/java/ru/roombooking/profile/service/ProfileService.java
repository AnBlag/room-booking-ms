package ru.roombooking.profile.service;


import ru.roombooking.profile.model.Profile;

public interface ProfileService extends RoomServiceCRUD<Profile, Long> {
    Profile findByLogin(String login);
    Profile findById(Long profId);
    boolean doesProfileExist(String login);
    Profile changeAccountNonLocked(Boolean account_non_locked, Long id);
}
