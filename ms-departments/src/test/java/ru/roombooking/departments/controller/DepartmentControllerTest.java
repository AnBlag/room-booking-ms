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
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.roombooking.departments.DepartmentsApplication;
import ru.roombooking.departments.model.Department;
import ru.roombooking.departments.service.impl.NotificationService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.*;
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
        List<Department> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

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

/*    @Test
    void saveDepartment_thenReturnNotOk() throws Exception {
        final String url = "/department/save/sdas";
        Department department = Department.builder()
                .id(3L)
                .nameDepartment("")
                .position("")
                .build();
        when(notificationService.saveDepartment(any())).thenReturn(department);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(department)))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        Department result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Department.class);
        assertNotEquals(200, mvcResult.getResponse().getStatus());
    }*/

    @Test
    void updateDepartment() {

    }

    @Test
    void deleteDepartment_thenReturnOk() throws Exception {
        final String url = "/department/delete/1";
        Department department = departmentList.get(0);
        when(notificationService.deleteDepartment("1")).thenReturn(department);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(url)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(department)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        Department result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Department.class);
        assertNull(notificationService.findById("1"));
        //assertEquals(department, result);
    }

    @Test
    void deleteDepartment_checkDeleting() throws Exception {
        final String url = "/department/delete/1";
        Department department = departmentList.get(0);
        List<Department> departments = notificationService.findAll();
        when(notificationService.deleteDepartment("1")).thenReturn(department);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(url)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(department)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();


        ObjectMapper objectMapper = new ObjectMapper();
        Department result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Department.class);
        assertNull(notificationService.findById("3"));
    }

    @Test
    void findById() {
    }

    @Test
    void batchUpdateDepartment() {
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}