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
import ru.roombooking.history.model.VscRoom;
import ru.roombooking.history.service.impl.VscRoomNotificationService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = {HistoryApplication.class})
class VscRoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VscRoomNotificationService vscRoomNotificationService;

    private List<VscRoom> vscRoomList;

    @BeforeEach
    void init() {
        vscRoomList = new ArrayList<>();
        vscRoomList.add(VscRoom.builder()
                .id(1L)
                .numberRoom(1L)
                .isActive(true)
                .build());
        vscRoomList.add(VscRoom.builder()
                .id(2L)
                .numberRoom(2L)
                .isActive(true)
                .build());
        vscRoomList.add(VscRoom.builder()
                .id(3L)
                .numberRoom(404L)
                .isActive(true)
                .build());
    }

    @Test
    void findAll_thenReturnOk() throws Exception {
        final String url = "/vsc_room/";
        when(vscRoomNotificationService.findAll()).thenReturn(vscRoomList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        List<VscRoom> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(vscRoomList, result);
    }

    @Test
    void saveRoom_thenReturnOk() throws Exception {
        final String url = "/vsc_room/save";
        VscRoom vscRoom = VscRoom.builder()
                .id(4L)
                .numberRoom(4L)
                .isActive(true)
                .build();
        when(vscRoomNotificationService.saveRoom(vscRoom)).thenReturn(vscRoom);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(vscRoom)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        VscRoom result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), VscRoom.class);
        assertEquals(vscRoom, result);
    }

    @Test
    void updateRoom_thenReturnOk() throws Exception {
        final String url = "/vsc_room/update/3";
        VscRoom vscRoom = VscRoom.builder()
                .id(3L)
                .numberRoom(303L)
                .isActive(false)
                .build();

        when(vscRoomNotificationService.updateRoom(vscRoom, "3")).thenReturn(vscRoom);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(vscRoom)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        VscRoom result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), VscRoom.class);
        assertEquals(vscRoom, result);
    }

    @Test
    void deleteRoom_thenReturnOk() throws Exception {
        final String url = "/vsc_room/delete/3";
        VscRoom vscRoom = vscRoomList.get(2);

        when(vscRoomNotificationService.deleteRoom("3")).thenReturn(vscRoom);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        VscRoom result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), VscRoom.class);
        assertEquals(vscRoom, result);
    }

    @Test
    void findByNumberRoomId_thenReturnOk() throws Exception {
        final String url = "/vsc_room/find-by-number-room-id/{id}";
        String id = "2";
        when(vscRoomNotificationService.findByNumberRoomId(id)).thenReturn(vscRoomList.get(1));
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url, id))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        VscRoom result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), VscRoom.class);

        assertEquals(vscRoomList.get(1), result);
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}