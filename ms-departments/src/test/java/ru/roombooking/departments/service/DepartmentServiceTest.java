package ru.roombooking.departments.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import ru.roombooking.departments.DepartmentsApplicationTests;
import ru.roombooking.departments.exception.DepartmentBadRequestException;
import ru.roombooking.departments.model.Department;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ContextConfiguration(classes = {DepartmentsApplicationTests.class})
public class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private TestEntityManager testEntityManager;

    private List<Department> departmentList;

    @BeforeEach
    void init() {
        departmentList = new ArrayList<>();

        departmentList.add(Department.builder()
                .id(1L)
                .nameDepartment("IT")
                .position("It Employees")
                .build());

        departmentList.add(Department.builder()
                .id(2L)
                .nameDepartment("Management")
                .position("Manager")
                .build());

        departmentList.add(Department.builder()
                .id(3L)
                .nameDepartment("Cleaning")
                .position("Cleaner")
                .build());

        initDb();
    }

    @Test
    void save_thenReturnOk() {
        Department department = Department.builder()
                .id(4L)
                .nameDepartment("Gaming")
                .position("Gamer")
                .build();
        assertEquals(department, departmentService.save(department));
    }

    @Test
    void update_thenReturnOk() {
        Department department = Department.builder()
                .id(3L)
                .nameDepartment("Cleaning")
                .position("Cleaner2")
                .build();
        Long id = departmentService.findAll().get(2).getId();
        department.setId(id);
        Department resultDepartment = departmentService.update(department, id);
        assertEquals(department, resultDepartment);
    }

    @Test
    void findAll_thenReturnOk() {
        List<Department> list = departmentService.findAll();
        assertEquals(list, departmentList);
    }

    @Test
    void deleteById_thenReturnOk() {
        Long id = departmentService.findAll().get(2).getId();
        Department departmentTemp = departmentService.findAll().get(2);
        Department department = departmentService.deleteById(id);
        assertEquals(departmentTemp, department);
    }

    @Test
    void deleteById_thenReturnFalse() {
        assertThrows(DepartmentBadRequestException.class, () -> {
            departmentService.deleteById(6L);
        });
    }

    @Test
    void findById_thenReturnOk() {
        Department department = departmentService.findAll().get(1);
        Department departmentResult = departmentService.findById(department.getId());
        assertEquals(department, departmentResult);
    }

    @Test
    void findById_thenReturnFalse() {
        assertThrows(DepartmentBadRequestException.class, () -> {
            departmentService.findById(6L);
        });
    }

    private void initDb() {
        testEntityManager.persist(Department.builder()
                .nameDepartment("IT")
                .position("It Employees")
                .build());
        testEntityManager.persist(Department.builder()
                .nameDepartment("Management")
                .position("Manager")
                .build());
        testEntityManager.persist(Department.builder()
                .nameDepartment("Cleaning")
                .position("Cleaner")
                .build());
    }
}