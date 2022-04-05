package ru.roombooking.history.controller;

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
import ru.roombooking.history.model.dto.RecordTableDTO;
import ru.roombooking.history.service.impl.HistoryNotificationService;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = {HistoryApplication.class})
class HistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoryNotificationService historyNotificationService;

    private List<RecordTableDTO> recordTableList;


    @BeforeEach
    void init() {
        recordTableList = new ArrayList<>();
        recordTableList.add(RecordTableDTO.builder()
                .id(1L)
                .email("zzz@mail.ru")
                .start(ZonedDateTime.parse("2021-10-21T08:00Z[UTC]"))
                .end(ZonedDateTime.parse("2021-10-21T09:00Z[UTC]"))
                .title("zzz")
                .isActive(true)
                .numberRoomId(11L)
                .employeeId(1L)
                .employeeName("Vasiliy")
                .employeeSurname("Maximov")
                .employeeMiddleName("Andreevich")
                .roomId("11")
                .build());
        recordTableList.add(RecordTableDTO.builder()
                .id(2L)
                .email("xxx@mail.ru")
                .start(ZonedDateTime.parse("2021-10-22T09:00Z[UTC]"))
                .end(ZonedDateTime.parse("2021-10-22T10:00Z[UTC]"))
                .title("xxx")
                .isActive(true)
                .numberRoomId(22L)
                .employeeId(2L)
                .employeeName("Konstantin")
                .employeeSurname("Popov")
                .employeeMiddleName("Alexeevich")
                .roomId("22")
                .build());
    }

    @Test
    void findAll_thenReturnOk() throws Exception {
        final String url = "/history/";
        when(historyNotificationService.findAll()).thenReturn(recordTableList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        List<RecordTableDTO> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(recordTableList, result);
    }

    @Test
    void deleteHistoryRecordById_thenReturnOk() throws Exception {
        final String url = "/history/delete-by-id/1";
        RecordTableDTO recordTableDTO = recordTableList.get(0);
        when(historyNotificationService.deleteById(1L)).thenReturn(recordTableDTO);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        RecordTableDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RecordTableDTO.class);
        assertEquals(recordTableDTO, result);
    }
}