package ru.roombooking.employee.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import ru.roombooking.employee.EmployeeApplicationTests;
import ru.roombooking.employee.feign.DepartmentFeignClient;
import ru.roombooking.employee.feign.ProfileFeignClient;
import ru.roombooking.employee.model.Employee;
import ru.roombooking.employee.model.dto.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = {EmployeeApplicationTests.class})
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TestEntityManager testEntityManager;

    private List<EmployeeDTO> employeeDTOList;

    @MockBean
    private ProfileFeignClient profileFeignClient;

    @MockBean
    private DepartmentFeignClient departmentFeignClient;

    @BeforeEach
    void init() {
        employeeDTOList = new ArrayList<>();
        employeeDTOList.add(EmployeeDTO.builder()
                .id(1L)
                .surname("Sidorov")
                .name("Valera")
                .middleName("Vladimirovich")
                .phone("111111")
                .email("zzz@mail.ru")
                .isActive(true)
                .profileId(1L)
                .departmentId(1L)
                .build());
        employeeDTOList.add(EmployeeDTO.builder()
                .id(2L)
                .surname("Popov")
                .name("Alexey")
                .middleName("Vadimovich")
                .phone("222222")
                .email("xxx@mail.ru")
                .isActive(true)
                .profileId(2L)
                .departmentId(1L)
                .build());
        employeeDTOList.add(EmployeeDTO.builder()
                .id(3L)
                .surname("Karpov")
                .name("Andrey")
                .middleName("Evgenevich")
                .phone("333333")
                .email("ccc@mail.ru")
                .isActive(true)
                .profileId(3L)
                .departmentId(1L)
                .build());

        initDb();
    }

    @Test
    void save_thenReturnOk() {
        EmployeeDTO employee = EmployeeDTO.builder()
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
        assertEquals(employee, employeeService.save(employee));
    }

    @Test
    void update_thenReturnOk() {
        EmployeeDTO employee = EmployeeDTO.builder()
                .id(1L)
                .surname("Sidortov_upd")
                .name("Alex_upd")
                .middleName("Alexeevich_upd")
                .phone("111111")
                .email("zzz@mail.ru")
                .isActive(true)
                .profileId(1L)
                .departmentId(1L)
                .build();
        Long id = employeeService.findAll().get(0).getId();
        employee.setId(id);
        EmployeeDTO resultEmployee = employeeService.update(employee, id);
        assertEquals(employee, resultEmployee);
    }

    @Test
    void findAll_thenReturnOk() {
        List<EmployeeDTO> list = employeeService.findAll();
        assertEquals(list, employeeDTOList);
    }

    @Test
    void deleteById_thenReturnOk() {
        Long id = employeeService.findAll().get(2).getId();
        EmployeeDTO employeeTemp = employeeService.findAll().get(2);
        EmployeeDTO employeeDTO = employeeService.deleteById(id);
        assertEquals(employeeTemp, employeeDTO);
    }

    @Test
    void findById_thenReturnOk() {
        EmployeeDTO employeeDTO = employeeService.findAll().get(1);
        EmployeeDTO employeeResult = employeeService.findById(employeeDTO.getId());
        assertEquals(employeeDTO, employeeResult);
    }

    private void initDb() {
        testEntityManager.persist(Employee.builder()
                .surname("Sidorov")
                .name("Valera")
                .middleName("Vladimirovich")
                .phone("111111")
                .email("zzz@mail.ru")
                .isActive(true)
                .profileId(1L)
                .departmentId(1L)
                .build());
        testEntityManager.persist(Employee.builder()
                .surname("Popov")
                .name("Alexey")
                .middleName("Vadimovich")
                .phone("222222")
                .email("xxx@mail.ru")
                .isActive(true)
                .profileId(2L)
                .departmentId(1L)
                .build());
        testEntityManager.persist(Employee.builder()
                .surname("Karpov")
                .name("Andrey")
                .middleName("Evgenevich")
                .phone("333333")
                .email("ccc@mail.ru")
                .isActive(true)
                .profileId(3L)
                .departmentId(1L)
                .build());
    }
}