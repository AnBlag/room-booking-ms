package ru.roombooking.profile.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import ru.roombooking.profile.ProfileApplicationTests;
import ru.roombooking.profile.model.Profile;
import ru.roombooking.profile.model.Role;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

        initDb();
    }

    @Test
    void save() {
        Profile profile = Profile.builder()
                .id(2L)
                .login("mute")
                .password("123")
                .role(Role.EMPLOYEE)
                .isActive(true)
                .accountNonLocked(true)
                .build();
        assertEquals(profile, profileService.save(profile));
    }

    private void initDb() {
        testEntityManager.persist(Profile.builder()
                .login("mute")
                .password("123")
                .role(Role.EMPLOYEE)
                .isActive(true)
                .accountNonLocked(true)
                .build());
    }
}