package ru.roombooking.departments.service;

import ru.roombooking.departments.model.Department;

import java.util.List;

public interface DepartmentService extends RoomServiceCRUD<Department, Long> {
    Department findById(Long aLong);

    void batchUpdateDepartment(List<Department> departmentList);
}
