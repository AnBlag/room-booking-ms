package ru.roombooking.employee.service;


import ru.roombooking.employee.model.Profile;
import ru.roombooking.employee.model.dto.EmployeeDTO;

public interface EmployeeService extends RoomServiceCRUD<EmployeeDTO, Long> {
    EmployeeDTO findByProfileID(Long profileID);
    EmployeeDTO findByLogin(String login);
    Boolean isPresentByDepartmentId(Long id);
    EmployeeDTO findById(Long aLong);
    Profile getProfileById(Long id);
}
