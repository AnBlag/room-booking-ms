package ru.roombooking.history.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import ru.roombooking.history.HistoryApplication;
import ru.roombooking.history.HistoryApplicationTests;
import ru.roombooking.history.model.VscRoom;
import ru.roombooking.history.model.dto.RecordTableDTO;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = {HistoryApplicationTests.class})
class VscRoomServiceTest {
    @Autowired
    private VscRoomService vscRoomService;

    @Autowired
    private TestEntityManager testEntityManager;

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

        initDb();
    }

    @Test
    void save_thenReturnOk() {
        VscRoom vscRoom = VscRoom.builder()
                .id(4L)
                .numberRoom(4L)
                .isActive(true)
                .build();
        assertEquals(vscRoom, vscRoomService.save(vscRoom));
    }

    @Test
    void findById() {
    }

    @Test
    void findByNumberRoomId() {
    }

    private void initDb() {
        testEntityManager.persist(VscRoom.builder()
                .numberRoom(1L)
                .isActive(true)
                .build());
        testEntityManager.persist(VscRoom.builder()
                .numberRoom(2L)
                .isActive(true)
                .build());
        testEntityManager.persist(VscRoom.builder()
                .numberRoom(404L)
                .isActive(true)
                .build());
    }
}