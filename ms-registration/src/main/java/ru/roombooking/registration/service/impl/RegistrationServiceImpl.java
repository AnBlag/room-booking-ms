package ru.roombooking.registration.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.roombooking.registration.exception.DepartmentBadRequestException;
import ru.roombooking.registration.exception.ProfileBadRequestException;
import ru.roombooking.registration.exception.EmployeeSaveException;
import ru.roombooking.registration.exception.ProfileSaveException;
import ru.roombooking.registration.feign.DepartmentFeignClient;
import ru.roombooking.registration.feign.EmployeeFeignClient;
import ru.roombooking.registration.feign.ProfileFeignClient;
import ru.roombooking.registration.mapper.VCMapper;
import ru.roombooking.registration.model.Profile;
import ru.roombooking.registration.model.dto.EmployeeDTO;
import ru.roombooking.registration.model.dto.RegistrationDTO;
import ru.roombooking.registration.service.RegistrationService;

@RequiredArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final EmployeeFeignClient employeeFeignClient;
    private final ProfileFeignClient profileFeignClient;
    private final DepartmentFeignClient departmentFeignClient;
    private final VCMapper<EmployeeDTO, RegistrationDTO> myEmployeeMapper;
    private final VCMapper<Profile, RegistrationDTO> myProfileMapper;

    @Override
    @Transactional
    public void saveEmployeeAndProfile(RegistrationDTO model) {

        try {
            profileFeignClient.saveProfile(myProfileMapper.toModel(model));
        } catch (FeignException e) {
            throw new ProfileSaveException();
        }

        try {
            employeeFeignClient.saveEmployee(toEmployee(model));
        } catch (FeignException e) {
            throw new EmployeeSaveException();
        }
    }

    private EmployeeDTO toEmployee(RegistrationDTO model) {
        EmployeeDTO employeeDTO = myEmployeeMapper.toModel(model);

        try {
            employeeDTO.setProfileId(profileFeignClient.findByLogin(model.getLogin()).getId());
        } catch (FeignException e) {
            throw new ProfileBadRequestException();
        }

        try {
            employeeDTO.setDepartmentId(departmentFeignClient.findById(String.valueOf(model.getDepartmentId())).getId());
        } catch (FeignException e) {
            throw new DepartmentBadRequestException();
        }

        return employeeDTO;
    }

    @Override
    public boolean doesUserExist(RegistrationDTO model) {
        return profileFeignClient.doesProfileExist(model.getLogin());
    }
}