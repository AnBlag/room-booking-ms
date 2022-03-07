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
import ru.roombooking.profile.model.Profile;
import ru.roombooking.profile.model.Role;
import ru.roombooking.profile.service.impl.NotificationService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
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
                .role(Role.EMPLOYEE)
                .isActive(true)
                .accountNonLocked(true)
                .build());
        /*profileList.add(Profile.builder()
                .id(2L)
                .nameDepartment("Security")
                .position("guard")
                .build());*/
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
    void saveDepartment_thenReturnOk() throws Exception {

    }

    @Test
    void deleteDepartment_thenReturnOk() {

    }

    @Test
    void delete_nonexistentDepartment() throws Exception {

    }

    @Test
    void findById_thenReturnOk() throws Exception {

    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}