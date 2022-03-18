package ru.roombooking.employee.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import ru.roombooking.employee.EmployeeApplication;
import ru.roombooking.employee.EmployeeApplicationTests;
import ru.roombooking.employee.model.dto.EmployeeDTO;
import ru.roombooking.employee.service.impl.EmployeeViewServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = {EmployeeApplication.class})
class EmployeeViewServiceTest {

    /*@Autowired
    private EmployeeViewServiceImpl employeeService;*/

    @Autowired
    private TestEntityManager testEntityManager;

    private List<EmployeeDTO> employeeDTOList;

    @BeforeEach
    void init() {
        /*employeeDTOList = new ArrayList<>();
        employeeDTOList.add(EmployeeDTO.builder()
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

        initDb();*/
    }

    @Test
    void save() {
        /*EmployeeDTO employee = EmployeeDTO.builder()
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
        assertEquals(employee, employeeService.save(employee));*/
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
    }
}