package ru.roombooking.history.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.roombooking.history.HistoryApplication;
import ru.roombooking.history.HistoryApplicationTests;
import ru.roombooking.history.model.dto.RecordTableDTO;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = {HistoryApplicationTests.class})
class RecordTableServiceTest {
    @Autowired
    private RecordTableService recordTableService;

    @Autowired
    private TestEntityManager testEntityManager;

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
        initDb();
    }

    @Test
    void save_thenReturnOk() {
        RecordTableDTO recordTableDTO = RecordTableDTO.builder()
                .id(4L)
                .email("zzz@mail.ru")
                .start(ZonedDateTime.parse("2022-05-01T15:00Z[UTC]"))
                .end(ZonedDateTime.parse("2022-05-01T16:00Z[UTC]"))
                .title("vvv")
                .isActive(true)
                .numberRoomId(11L)
                .employeeId(1L)
                .employeeName("Vasiliy")
                .employeeSurname("Maximov")
                .employeeMiddleName("Andreevich")
                .roomId("11")
                .timeZone(TimeZone.getTimeZone("Europe/Moscow").toZoneId())
                .vcsRoomNumberRoom("11")
                .build();
        assertEquals(recordTableDTO, recordTableService.save(recordTableDTO));
    }

    @Test
    void findAllByEmployeeFullNameAndRecordAndIsActiveAndNumberRoom() {
    }

    @Test
    void delete() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByNumberRoom() {
    }

    private void initDb() {
        testEntityManager.persist(RecordTableDTO.builder()
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
        testEntityManager.persist(RecordTableDTO.builder()
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
        testEntityManager.persist(RecordTableDTO.builder()
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
}