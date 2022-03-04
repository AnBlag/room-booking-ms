package ru.roombooking.employee.controller;

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
import ru.roombooking.employee.model.dto.EmployeeDTO;
import ru.roombooking.employee.service.impl.NotificationService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = {EmployeeController.class})
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    private List<EmployeeDTO> employeeList;

    @BeforeEach
    void init() {
        employeeList = new ArrayList<>();
        employeeList.add(EmployeeDTO.builder()
                .id(1L)
                .surname("Sidorov")
                .name("Valera")
                .middleName("Vladimirovich")
                .phone("222222")
                .email("zzz@mail.ru")
                .isActive(true)
                .profileId(1L)
                .departmentId(1L)
                .build());
        employeeList.add(EmployeeDTO.builder()
                .id(2L)
                .surname("Moiseev")
                .name("Vitaliy")
                .middleName("Vladimirovich")
                .phone("111111")
                .email("xxx@mail.ru")
                .isActive(true)
                .profileId(2L)
                .departmentId(1L)
                .build());
        employeeList.add(EmployeeDTO.builder()
                .id(1L)
                .surname("Petrov")
                .name("Ivan")
                .middleName("Fedorovich")
                .phone("333333")
                .email("ccc@mail.ru")
                .isActive(true)
                .profileId(3L)
                .departmentId(2L)
                .build());
    }

    @Test
    void findAll_thenReturnOk() throws Exception {
        final String url = "/employee/";
        when(notificationService.findAll()).thenReturn(employeeList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        List<EmployeeDTO> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(employeeList, result);
    }

    @Test
    void saveEmployee() {
    }

    @Test
    void restoreEmployee() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void findByProfileID() {
    }

    @Test
    void findByLogin() {
    }

    @Test
    void getProfileById() {
    }

    @Test
    void findById() {
    }
}