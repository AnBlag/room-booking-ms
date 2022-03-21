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
import ru.roombooking.history.model.dto.RecordTableDTO;
import ru.roombooking.history.service.impl.RecordTableNotificationService;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = {HistoryApplication.class})
class RecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecordTableNotificationService recordTableNotificationService;

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
        recordTableList.add(RecordTableDTO.builder()
                .id(3L)
                .email("zzz@mail.ru")
                .start(ZonedDateTime.parse("2022-05-01T11:00Z[UTC]"))
                .end(ZonedDateTime.parse("2022-05-01T12:00Z[UTC]"))
                .title("zzz")
                .isActive(true)
                .numberRoomId(11L)
                .employeeId(1L)
                .employeeName("Vasiliy")
                .employeeSurname("Maximov")
                .employeeMiddleName("Andreevich")
                .roomId("11")
                .build());
    }

    @Test
    void findAll_thenReturnOk() throws Exception {
        final String url = "/record/";
        when(recordTableNotificationService.findAll()).thenReturn(recordTableList);
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
    void findByIndex_thenReturnOk() throws Exception {
        final String url = "/record/11";
        List<RecordTableDTO> resultRecordTableList = new ArrayList<>();
        resultRecordTableList.add(RecordTableDTO.builder()
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
        resultRecordTableList.add(RecordTableDTO.builder()
                .id(3L)
                .email("zzz@mail.ru")
                .start(ZonedDateTime.parse("2022-05-01T11:00Z[UTC]"))
                .end(ZonedDateTime.parse("2022-05-01T12:00Z[UTC]"))
                .title("zzz")
                .isActive(true)
                .numberRoomId(11L)
                .employeeId(1L)
                .employeeName("Vasiliy")
                .employeeSurname("Maximov")
                .employeeMiddleName("Andreevich")
                .roomId("11")
                .build());
        when(recordTableNotificationService.findByIndex("11")).thenReturn(resultRecordTableList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        List<RecordTableDTO> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(resultRecordTableList, result);
    }

    @Test
    void saveRecord_thenReturnOk() throws Exception {
        final String url = "/record/save/Vasilisk";
        RecordTableDTO recordTableDTO = RecordTableDTO.builder()
                .id(4L)
                .email("ccc@mail.ru")
                .start(ZonedDateTime.parse("2022-01-11T13:00Z[UTC]"))
                .end(ZonedDateTime.parse("2022-01-11T14:00Z[UTC]"))
                .title("ccc")
                .isActive(true)
                .numberRoomId(22L)
                .employeeId(3L)
                .employeeName("Pavel")
                .employeeSurname("Pavlov")
                .employeeMiddleName("Pavlovich")
                .roomId("22")
                .build();
        when(recordTableNotificationService.saveRecord(recordTableDTO, "Vasilisk")).thenReturn(recordTableDTO);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(recordTableDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        RecordTableDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RecordTableDTO.class);
        assertEquals(recordTableDTO, result);
    }

    @Test
    void updateRecord_thenReturnOk() throws Exception {
        final String url = "/record/update/XXX";
        RecordTableDTO recordTableDTO = RecordTableDTO.builder()
                .id(2L)
                .email("xxx@mail.ru")
                .start(ZonedDateTime.parse("2021-10-22T11:00Z[UTC]"))
                .end(ZonedDateTime.parse("2021-10-22T12:00Z[UTC]"))
                .title("xxx")
                .isActive(true)
                .numberRoomId(22L)
                .employeeId(2L)
                .employeeName("Konstantin")
                .employeeSurname("Popov")
                .employeeMiddleName("Alexeevich")
                .roomId("22")
                .build();
        when(recordTableNotificationService.updateRecord(recordTableDTO, "XXX")).thenReturn(recordTableDTO);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(recordTableDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        RecordTableDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RecordTableDTO.class);
        assertEquals(recordTableDTO, result);
    }

    @Test
    void deleteRecord_thenReturnOk() throws Exception {
        final String url = "/record/delete/XXX";
        RecordTableDTO recordTableDTO = recordTableList.get(1);
        when(recordTableNotificationService.deleteRecord(recordTableDTO, "XXX")).thenReturn(recordTableDTO);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(url)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(recordTableDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        RecordTableDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RecordTableDTO.class);
        assertEquals(recordTableDTO, result);
    }

    @Test
    void deleteRecordById_thenReturnOk() throws Exception {
        final String url = "/record/delete-by-id/2";
        RecordTableDTO recordTableDTO = recordTableList.get(1);
        when(recordTableNotificationService.deleteRecordById(any())).thenReturn(recordTableDTO);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        RecordTableDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RecordTableDTO.class);
        assertEquals(recordTableDTO, result);
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(obj);
    }
}