package ru.roombooking.employee.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import ru.roombooking.employee.EmployeeApplicationTests;
import ru.roombooking.employee.model.dto.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@ContextConfiguration(classes = {EmployeeApplicationTests.class})
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TestEntityManager testEntityManager;

    private List<EmployeeDTO> employeeList;

    @BeforeEach
    void init() {
        employeeList = new ArrayList<>();

        employeeList.add(EmployeeDTO.builder()
                .id(1L)
                .surname("Sidorov")
                .name("Valera")
                .middleName("Vladimirovich")
                .phone("222222")
                .email("zzz@mail.ru")
                .isActive(true)
                .profileId(1L)
                .departmentId(1L)
                .build());
        employeeList.add(EmployeeDTO.builder()
                .id(2L)
                .surname("Moiseev")
                .name("Vitaliy")
                .middleName("Vladimirovich")
                .phone("111111")
                .email("xxx@mail.ru")
                .isActive(true)
                .profileId(2L)
                .departmentId(1L)
                .build());
        employeeList.add(EmployeeDTO.builder()
                .id(3L)
                .surname("Petrov")
                .name("Ivan")
                .middleName("Fedorovich")
                .phone("333333")
                .email("ccc@mail.ru")
                .isActive(true)
                .profileId(3L)
                .departmentId(2L)
                .build());

        initDb();
    }

    @Test
    void save() {
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .id(4L)
                .surname("Sidortov")
                .name("Alex")
                .middleName("Alexeevich")
                .phone("444444")
                .email("vvv@mail.ru")
                .isActive(true)
                .profileId(4L)
                .departmentId(2L)
                .build();
        assertEquals(employeeDTO, employeeService.save(employeeDTO));
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {
    }

    private void initDb() {
        testEntityManager.persist(EmployeeDTO.builder()
                .surname("Sidorov")
                .name("Valera")
                .middleName("Vladimirovich")
                .phone("222222")
                .email("zzz@mail.ru")
                .isActive(true)
                .profileId(1L)
                .departmentId(1L)
                .build());
        testEntityManager.persist(EmployeeDTO.builder()
                .surname("Moiseev")
                .name("Vitaliy")
                .middleName("Vladimirovich")
                .phone("111111")
                .email("xxx@mail.ru")
                .isActive(true)
                .profileId(2L)
                .departmentId(1L)
                .build());
        testEntityManager.persist(EmployeeDTO.builder()
                .surname("Petrov")
                .name("Ivan")
                .middleName("Fedorovich")
                .phone("333333")
                .email("ccc@mail.ru")
                .isActive(true)
                .profileId(3L)
                .departmentId(2L)
                .build());
    }
}