package ru.roombooking.history.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.roombooking.history.HistoryApplication;
import ru.roombooking.history.model.RecordTableView;
import ru.roombooking.history.service.impl.RecordTableViewNotificationService;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = {HistoryApplication.class})
class RecordTableViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecordTableViewNotificationService recordTableViewNotificationService;

    private List<RecordTableView> recordTableViewList;

    @BeforeEach
    void init() {
        recordTableViewList = new ArrayList<>();
        recordTableViewList.add(RecordTableView.builder()
                .id(1L)
                .email("zzz@mail.ru")
                .startEvent(ZonedDateTime.parse("2021-10-21T08:00Z[UTC]"))
                .endEvent(ZonedDateTime.parse("2021-10-21T09:00Z[UTC]"))
                .title("zzz")
                .isActive(true)
                .vcsRoomNumberRoom(11L)
                .employeeId(1L)
                .employeeName("Vasiliy")
                .employeeSurname("Maximov")
                .employeeMiddleName("Andreevich")
                .build());
        recordTableViewList.add(RecordTableView.builder()
                .id(2L)
                .email("xxx@mail.ru")
                .startEvent(ZonedDateTime.parse("2021-10-22T09:00Z[UTC]"))
                .endEvent(ZonedDateTime.parse("2021-10-22T10:00Z[UTC]"))
                .title("xxx")
                .isActive(true)
                .vcsRoomNumberRoom(22L)
                .employeeId(2L)
                .employeeName("Konstantin")
                .employeeSurname("Popov")
                .employeeMiddleName("Alexeevich")
                .build());
        recordTableViewList.add(RecordTableView.builder()
                .id(3L)
                .email("zzz@mail.ru")
                .startEvent(ZonedDateTime.parse("2022-05-01T11:00Z[UTC]"))
                .endEvent(ZonedDateTime.parse("2022-05-01T12:00Z[UTC]"))
                .title("zzz")
                .isActive(true)
                .vcsRoomNumberRoom(11L)
                .employeeId(1L)
                .employeeName("Vasiliy")
                .employeeSurname("Maximov")
                .employeeMiddleName("Andreevich")
                .build());
    }

    @Test
    void findAll_thenReturnOk() throws Exception {
        final String url = "/record-table-view/";
        when(recordTableViewNotificationService.findAll()).thenReturn(recordTableViewList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        List<RecordTableView> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(recordTableViewList, result);
    }

    @Test
    void getRecordTableViewListByURLParams_thenReturnOk() throws Exception {
        final String url = "/record-table-view/get-record-table-view-list-by-URL-params?search=id:1";
        String search = "id:1";
        List<RecordTableView> resultRecordTableViewList = new ArrayList<>();
        resultRecordTableViewList.add(RecordTableView.builder()
                .id(1L)
                .email("zzz@mail.ru")
                .startEvent(ZonedDateTime.parse("2021-10-21T08:00Z[UTC]"))
                .endEvent(ZonedDateTime.parse("2021-10-21T09:00Z[UTC]"))
                .title("zzz")
                .isActive(true)
                .vcsRoomNumberRoom(11L)
                .employeeId(1L)
                .employeeName("Vasiliy")
                .employeeSurname("Maximov")
                .employeeMiddleName("Andreevich")
                .build());

        when(recordTableViewNotificationService.getRecordTableViewListByURLParams(search)).thenReturn(resultRecordTableViewList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        List<RecordTableView> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(resultRecordTableViewList, result);
    }

    @Test
    void getRecordTableViewListByRecordTableViewParams_thenReturnOk() throws Exception {
        final String url = "/record-table-view/get-record-table-view-list-by-record-table-view-params";
        List<RecordTableView> resultRecordTableViewList = new ArrayList<>();
        resultRecordTableViewList.add(RecordTableView.builder()
                .id(1L)
                .email("zzz@mail.ru")
                .startEvent(ZonedDateTime.parse("2021-10-21T08:00Z[UTC]"))
                .endEvent(ZonedDateTime.parse("2021-10-21T09:00Z[UTC]"))
                .title("zzz")
                .isActive(true)
                .vcsRoomNumberRoom(11L)
                .employeeId(1L)
                .employeeName("Vasiliy")
                .employeeSurname("Maximov")
                .employeeMiddleName("Andreevich")
                .build());

        when(recordTableViewNotificationService.getRecordTableViewListByRecordTableViewParams(recordTableViewList.get(0))).thenReturn(resultRecordTableViewList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(recordTableViewList.get(0))))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        List<RecordTableView> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(resultRecordTableViewList, result);
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(obj);
    }
}