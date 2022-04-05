package ru.roombooking.profile.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.roombooking.profile.ProfileApplication;
import ru.roombooking.profile.exception.ProfileDeleteException;
import ru.roombooking.profile.model.Profile;
import ru.roombooking.profile.model.Role;
import ru.roombooking.profile.service.impl.NotificationService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ProfileApplication.class})
class ProfileControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    private List<Profile> profileList;

    @BeforeEach
    void init() {
        profileList = new ArrayList<>();
        profileList.add(Profile.builder()
                .id(1L)
                .login("root")
                .password("root")
                .role(Role.ADMIN)
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
    }

    @Test
    void findAll_thenReturnOk() throws Exception {
        final String url = "/profile/";
        when(notificationService.findAll()).thenReturn(profileList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Profile> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(profileList, result);
    }

    @Test
    void saveProfile_thenReturnOk() throws Exception {
        final String url = "/profile/save";
        Profile profile = Profile.builder()
                .id(3L)
                .login("manager2")
                .password("root")
                .role(Role.EMPLOYEE)
                .isActive(true)
                .accountNonLocked(true)
                .build();
        when(notificationService.saveProfile(any())).thenReturn(profile);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(profile)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        Profile result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Profile.class);
        assertEquals(profile, result);
    }

    @Test
    void deleteProfile_thenReturnOk() {
        Profile profile = profileList.get(0);
        when(notificationService.deleteProfile("1")).thenReturn(profile);
        assertNull(notificationService.findByID("1"));
    }

    @Test
    void delete_nonexistentProfile() throws Exception {
        final String url = "/profile/delete/10";
        when(notificationService.deleteProfile("10")).thenThrow(ProfileDeleteException.class);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(url)
                .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
    }

    @Test
    void findById_thenReturnOk() throws Exception {
        final String url = "/profile/find-by-id/{id}";
        String id = "1";
        when(notificationService.findByID(id)).thenReturn(profileList.get(0));
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url, id))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        Profile result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Profile.class);

        assertEquals(profileList.get(0), result);
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}