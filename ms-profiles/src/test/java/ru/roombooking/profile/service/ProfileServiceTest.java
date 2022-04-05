package ru.roombooking.profile.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import ru.roombooking.profile.ProfileApplicationTests;
import ru.roombooking.profile.exception.ProfileDeleteException;
import ru.roombooking.profile.exception.ProfileNotFoundException;
import ru.roombooking.profile.model.Profile;
import ru.roombooking.profile.model.Role;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ContextConfiguration(classes = {ProfileApplicationTests.class})
class ProfileServiceTest {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private TestEntityManager testEntityManager;

    private List<Profile> profileList;

    @BeforeEach
    void init() {
        profileList = new ArrayList<>();
        profileList.add(Profile.builder()
                .id(1L)
                .login("root")
                .password("root")
                .role(Role.EMPLOYEE)
                .isActive(true)
                .accountNonLocked(true)
                .build());

        profileList.add(Profile.builder()
                .id(2L)
                .login("manager1")
                .password("root")
                .role(Role.EMPLOYEE)
                .isActive(true)
                .accountNonLocked(true)
                .build());

        initDb();
    }

    @Test
    void save() {
        Profile profile = Profile.builder()
                .id(3L)
                .login("mute")
                .password("123")
                .role(Role.EMPLOYEE)
                .isActive(true)
                .accountNonLocked(true)
                .build();
        assertEquals(profile, profileService.save(profile));
    }

    @Test
    void update_thenReturnOk() {
        Profile profile = Profile.builder()
                .id(2L)
                .login("manager1_upd")
                .password("root")
                .role(Role.EMPLOYEE)
                .isActive(false)
                .accountNonLocked(true)
                .build();
        Long id = profileService.findAll().get(1).getId();
        profile.setId(id);
        Profile resultProfile = profileService.update(profile, id);
        assertEquals(profile, resultProfile);
    }

    @Test
    void findAll_thenReturnOk() {
        List<Profile> list = profileService.findAll();
        assertEquals(list, profileList);
    }

    @Test
    void deleteById_thenReturnOk() {
        Long id = profileService.findAll().get(2).getId();
        Profile profileTemp = profileService.findAll().get(2);
        Profile profile = profileService.deleteById(id);
        assertEquals(profileTemp, profile);
    }

    @Test
    void deleteById_thenReturnFalse() {
        assertThrows(ProfileDeleteException.class, () -> {
            profileService.deleteById(6L);
        });
    }

    @Test
    void findById_thenReturnOk() {
        Profile profile = profileService.findAll().get(1);
        Profile profileResult = profileService.findById(profile.getId());
        assertEquals(profile, profileResult);
    }

    @Test
    void findById_thenReturnFalse() {
        assertThrows(ProfileNotFoundException.class, () -> {
            profileService.findById(6L);
        });
    }

    private void initDb() {
        testEntityManager.persist(Profile.builder()
                .login("root")
                .password("root")
                .role(Role.EMPLOYEE)
                .isActive(true)
                .accountNonLocked(true)
                .build());

        testEntityManager.persist(Profile.builder()
                .login("manager1")
                .password("root")
                .role(Role.EMPLOYEE)
                .isActive(true)
                .accountNonLocked(true)
                .build());
    }
}