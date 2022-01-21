package ru.roombooking.front.service;


import ru.roombooking.front.model.dto.EmployeeDTO;
import ru.roombooking.front.model.dto.ProfileDTO;

public interface EmployeeAndProfileService {
    void update(EmployeeDTO employeeDTO, ProfileDTO profile);
    //void deleteByProfileId(Long aLong);
    //EmployeeDTO findEmployeeByProfileId(Long aLong);
    ProfileDTO findProfileById(Long aLong);
    EmployeeDTO findByLogin(String login);
}
