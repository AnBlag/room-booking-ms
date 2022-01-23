package ru.roombooking.admin.service;

import ru.roombooking.admin.model.dto.ProfileDTO;
import ru.roombooking.admin.model.dto.EmployeeDTO;

public interface EmployeeAndProfileService {
    void update(EmployeeDTO employeeDTO, ProfileDTO profile);

    void deleteByProfileId(Long aLong);

    EmployeeDTO findEmployeeByProfileId(Long aLong);

    ProfileDTO findProfileById(Long aLong);
}