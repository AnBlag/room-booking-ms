package ru.roombooking.departments.controller;

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
import ru.roombooking.departments.DepartmentsApplication;
import ru.roombooking.departments.exception.DepartmentDeleteException;
import ru.roombooking.departments.model.Department;
import ru.roombooking.departments.service.impl.NotificationService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        departmentList.add(Department.builder()
                .id(2L)
                .nameDepartment("Security")
                .position("guard")
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
        List<Department> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(departmentList, result);
    }

    @Test
    void saveDepartment_thenReturnOk() throws Exception {
        final String url = "/department/save";
        Department department = Department.builder()
                .id(3L)
                .nameDepartment("CleaningService")
                .position("cleaner")
                .build();
        when(notificationService.saveDepartment(any())).thenReturn(department);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(department)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        Department result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Department.class);
        assertEquals(department, result);
    }

    @Test
    void deleteDepartment_thenReturnOk() {
        final String url = "/department/delete/1";
        Department department = departmentList.get(0);
        when(notificationService.deleteDepartment("1")).thenReturn(department);
        assertNull(notificationService.findById("1"));
    }

    @Test
    void delete_nonexistentDepartment() throws Exception {
        final String url = "/department/delete/10";
        when(notificationService.deleteDepartment("10")).thenThrow(DepartmentDeleteException.class);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(url)
                .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
    }

    @Test
    void findById_thenReturnOk() throws Exception {
        final String url = "/department/find-by-id/{id}";
        String id = "1";
        when(notificationService.findById(id)).thenReturn(departmentList.get(0));
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url, id))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        Department result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Department.class);

        assertEquals(departmentList.get(0), result);
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}