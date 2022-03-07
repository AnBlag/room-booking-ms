package ru.roombooking.employee.controller;

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
import ru.roombooking.employee.EmployeeApplication;
import ru.roombooking.employee.model.dto.EmployeeDTO;
import ru.roombooking.employee.service.impl.NotificationService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = {EmployeeApplication.class})
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
                .id(3L)
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
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        List<EmployeeDTO> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(employeeList, result);
    }

    @Test
    void saveEmployee_thenReturnOk() throws Exception {
        final String url = "/employee/save";
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .id(4L)
                .surname("Sidortov")
                .name("Alex")
                .middleName("Alexeevich")
                .phone("444444")
                .email("vvv@mail.ru")
                .isActive(true)
                .profileId(4L)
                .departmentId(2L)
                .build();
        when(notificationService.saveEmployee(any())).thenReturn(employeeDTO);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(employeeDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        EmployeeDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), EmployeeDTO.class);
        assertEquals(employeeDTO, result);
    }

    @Test
    void updateEmployee_thenReturnOk() throws Exception {
        final String url = "/employee/update/4";
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .surname("Belkin_upd")
                .name("Mikhail_upd")
                .middleName("Olegovich_upd")
                .phone("555555")
                .email("bbb@mail.ru")
                .isActive(true)
                .profileId(4L)
                .departmentId(2L)
                .build();
        when(notificationService.updateEmployee(employeeDTO, "4")).thenReturn(employeeDTO);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(employeeDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        EmployeeDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), EmployeeDTO.class);
        assertEquals(employeeDTO, result);
    }

    @Test
    void deleteEmployee_thenReturnOk() throws Exception {
        final String url = "/employee/delete/1";
        EmployeeDTO employeeDTO = employeeList.get(0);
        when(notificationService.deleteEmployee("1")).thenReturn(employeeDTO);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(url)
                .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        EmployeeDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), EmployeeDTO.class);
        assertEquals(employeeDTO, result);
    }

    @Test
    void findByProfileID() throws Exception {
        final String url = "/employee/find-by-profile/{profileId}";
        String profileId = "1";
        when(notificationService.findByProfileID(profileId)).thenReturn(employeeList.get(0));
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url, profileId))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        EmployeeDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), EmployeeDTO.class);

        assertEquals(employeeList.get(0), result);
    }

    @Test
    void findById_thenReturnOk() throws Exception {
        final String url = "/employee/find-by-id/{id}";
        String id = "1";
        when(notificationService.findById(id)).thenReturn(employeeList.get(0));
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url, id))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        EmployeeDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), EmployeeDTO.class);

        assertEquals(employeeList.get(0), result);
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}