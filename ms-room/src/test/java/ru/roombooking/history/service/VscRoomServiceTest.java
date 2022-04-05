package ru.roombooking.history.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import ru.roombooking.history.HistoryApplicationTests;
import ru.roombooking.history.feign.EmployeeFeignClient;
import ru.roombooking.history.feign.MailFeignClient;
import ru.roombooking.history.feign.ProfileFeignClient;
import ru.roombooking.history.model.VscRoom;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = {HistoryApplicationTests.class})
class VscRoomServiceTest {
    @Autowired
    private VscRoomService vscRoomService;

    @Autowired
    private TestEntityManager testEntityManager;

    @MockBean
    private EmployeeFeignClient employeeFeignClient;

    @MockBean
    private ProfileFeignClient profileFeignClient;

    @MockBean
    private MailFeignClient mailFeignClient;

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
    void update_thenReturnOk() {
        VscRoom vscRoom = VscRoom.builder()
                .id(1L)
                .numberRoom(101L)
                .isActive(true)
                .build();
        Long id = vscRoomService.findAll().get(0).getId();
        vscRoom.setId(id);
        VscRoom resultVscRoom = vscRoomService.update(vscRoom, id);
        assertEquals(vscRoom, resultVscRoom);
    }

    @Test
    void findAll_thenReturnOk() {
        List<VscRoom> list = vscRoomService.findAll();
        assertEquals(list, vscRoomList);
    }

    @Test
    void delete_thenReturnOk() {
        Long id = vscRoomService.findAll().get(0).getId();
        VscRoom vscRoomTemp = vscRoomService.findAll().get(0);
        VscRoom vscRoom = vscRoomService.deleteById(id);
        assertEquals(vscRoomTemp, vscRoom);
    }

    @Test
    void findById_thenReturnOk() {
        VscRoom vscRoom = vscRoomService.findAll().get(1);
        VscRoom vscRoomResult = vscRoomService.findById(vscRoom.getId());
        assertEquals(vscRoom, vscRoomResult);
    }

    @Test
    void findByNumberRoomId_thenReturnOk() {
        VscRoom vscRoom = vscRoomService.findAll().get(1);
        VscRoom vscRoomResult = vscRoomService.findById(vscRoom.getId());
        assertEquals(vscRoom, vscRoomResult);
    }

    @Test
    void findByNumberRoomId() {
        VscRoom vscRoom = vscRoomService.findAll().get(1);
        VscRoom vscRoomResult = vscRoomService.findByNumberRoomId(vscRoom.getNumberRoom());
        assertEquals(vscRoom, vscRoomResult);
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