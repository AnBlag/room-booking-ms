package ru.roombooking.admin.service;


import ru.roombooking.admin.model.Profile;
import ru.roombooking.admin.model.dto.EmployeeDTO;

public interface EmployeeAndProfileService {
    void update(EmployeeDTO employeeDTO, Profile profile);
    void deleteByProfileId(Long aLong);
    EmployeeDTO findEmployeeByProfileId(Long aLong);
    Profile findProfileById(Long aLong);
    EmployeeDTO findByLogin(String login);
}
