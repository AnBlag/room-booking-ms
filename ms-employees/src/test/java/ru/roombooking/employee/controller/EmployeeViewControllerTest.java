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
import ru.roombooking.employee.model.EmployeeView;
import ru.roombooking.employee.model.dto.EmployeeDTO;
import ru.roombooking.employee.service.impl.EmployeeViewServiceImpl;
import ru.roombooking.employee.service.impl.NotificationService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = {EmployeeApplication.class})
class EmployeeViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeViewServiceImpl employeeViewService;

    @MockBean
    private NotificationService notificationService;

    private List<EmployeeView> employeeViewList;

    @BeforeEach
    void init() {
        employeeViewList = new ArrayList<>();
        employeeViewList.add(EmployeeView.builder()
                .id(1L)
                .surname("Sidorov")
                .name("Valera")
                .middleName("Vladimirovich")
                .phone("222222")
                .email("zzz@mail.ru")
                .banned(true)
                .build());
        employeeViewList.add(EmployeeView.builder()
                .id(2L)
                .surname("Moiseev")
                .name("Vitaliy")
                .middleName("Vladimirovich")
                .phone("111111")
                .email("xxx@mail.ru")
                .banned(true)
                .build());
        employeeViewList.add(EmployeeView.builder()
                .id(3L)
                .surname("Petrov")
                .name("Ivan")
                .middleName("Fedorovich")
                .phone("333333")
                .email("ccc@mail.ru")
                .banned(true)
                .build());

    }

    @Test
    void findAll_thenReturnOk() throws Exception {
        final String url = "/employee-view/";
        when(employeeViewService.findAll()).thenReturn(employeeViewList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        List<EmployeeView> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(employeeViewList, result);
    }

    @Test
    void saveEmployeeView_thenReturnOk() throws Exception {
        final String url = "/employee-view/save";
        EmployeeView employeeView = EmployeeView.builder()
                .id(4L)
                .surname("Sidortov")
                .name("Alex")
                .middleName("Alexeevich")
                .phone("444444")
                .email("vvv@mail.ru")
                .banned(true)
                .build();
        when(employeeViewService.save(any())).thenReturn(employeeView);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(employeeView)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        EmployeeView result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), EmployeeView.class);
        assertEquals(employeeView, result);
    }

    /*@Test
    void batchUpdateProfileAndEmployee_thenReturnOk() throws Exception {
        final String url = "/employee-view/batch-update-profile-and-employee";
        List<EmployeeView> updatedEmployeeViewList = new ArrayList<>();
        updatedEmployeeViewList.add(EmployeeView.builder()
                .id(1L)
                .surname("Sidorov_upd")
                .name("Valera_upd")
                .middleName("Vladimirovich_upd")
                .phone("222222")
                .email("zzz@mail.ru")
                .banned(true)
                .build());
        updatedEmployeeViewList.add(EmployeeView.builder()
                .id(2L)
                .surname("Moiseev_upd")
                .name("Vitaliy_upd")
                .middleName("Vladimirovich_upd")
                .phone("111111")
                .email("xxx@mail.ru")
                .banned(true)
                .build());
        updatedEmployeeViewList.add(EmployeeView.builder()
                .id(3L)
                .surname("Petrov_upd")
                .name("Ivan_upd")
                .middleName("Fedorovich_upd")
                .phone("333333")
                .email("ccc@mail.ru")
                .banned(true)
                .build());
        doNothing().when(employeeViewService).batchUpdateProfileAndEmployee(updatedEmployeeViewList);

        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

    }*/

    @Test
    void getEmployeeViewListByURLParams_thenReturnOk() throws Exception {
        final String url = "/employee-view/get-employee-view-list-by-URL-params?search=id:1";
        String search = "id:1";

        List<EmployeeView> resultEmployeeViewList = new ArrayList<>();
        resultEmployeeViewList.add(EmployeeView.builder()
                .id(1L)
                .surname("Sidorov")
                .name("Valera")
                .middleName("Vladimirovich")
                .phone("222222")
                .email("zzz@mail.ru")
                .banned(true)
                .build());

        when(notificationService.getEmployeeViewListByURLParams(search)).thenReturn(resultEmployeeViewList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        List<EmployeeView> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(resultEmployeeViewList, result);
    }

    @Test
    void getEmployeeViewListByEmployeeViewParams_thenReturnOk() throws Exception {
        final String url = "/employee-view/get-employee-view-list-by-employee-view-params";

        List<EmployeeView> resultEmployeeViewList = new ArrayList<>();
        resultEmployeeViewList.add(EmployeeView.builder()
                .id(1L)
                .surname("Sidorov")
                .name("Valera")
                .middleName("Vladimirovich")
                .phone("222222")
                .email("zzz@mail.ru")
                .banned(true)
                .build());

        when(notificationService.getEmployeeViewListByEmployeeViewParams(resultEmployeeViewList.get(0))).thenReturn(resultEmployeeViewList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(resultEmployeeViewList.get(0))))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        List<EmployeeView> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(resultEmployeeViewList, result);
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}