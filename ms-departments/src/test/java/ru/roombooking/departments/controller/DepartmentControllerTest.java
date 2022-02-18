package ru.roombooking.departments.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.roombooking.departments.DepartmentsApplication;
import ru.roombooking.departments.model.Department;
import ru.roombooking.departments.service.impl.NotificationService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = {DepartmentsApplication.class})
class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    private List<Department> departmentList;

    @BeforeEach
    void init() {
        departmentList = new ArrayList<>();
        departmentList.add(Department.builder()
                .id(1L)
                .nameDepartment("Management")
                .position("manager")
                .build());
    }

    @Test
    void findAll_thenReturnOk() throws Exception {
        final String url = "/department/";
        when(notificationService.findAll()).thenReturn(departmentList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Department> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

        assertEquals(departmentList, result);
    }

    @Test
    void saveDepartment() {
    }

    @Test
    void updateDepartment() {
    }

    @Test
    void deleteDepartment() {
    }

    @Test
    void findById() {
    }

    @Test
    void batchUpdateDepartment() {
    }
}