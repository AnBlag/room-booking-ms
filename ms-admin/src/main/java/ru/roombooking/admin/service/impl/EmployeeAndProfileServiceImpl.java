package ru.roombooking.admin.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.admin.exception.UsersUpdateException;
import ru.roombooking.admin.feign.EmployeeFeignClient;
import ru.roombooking.admin.feign.ProfileFeignClient;
import ru.roombooking.admin.model.dto.ProfileDTO;
import ru.roombooking.admin.model.dto.EmployeeDTO;
import ru.roombooking.admin.service.EmployeeAndProfileService;

@Service
@RequiredArgsConstructor
public class EmployeeAndProfileServiceImpl implements EmployeeAndProfileService {
    private final EmployeeFeignClient employeeFeignClient;
    private final ProfileFeignClient profileFeignClient;

    // FIXME: 31.01.2022 вопрос с синхронным update и delete
    @Override
    public void update(EmployeeDTO employeeDTO, ProfileDTO profile) {
        try {
            employeeFeignClient.saveEmployee(employeeDTO);
            profileFeignClient.saveProfile(profile);
        } catch (FeignException e) {
            throw new UsersUpdateException();
        }
    }

    @Override
    public void deleteByProfileId(Long aLong) {
        employeeFeignClient.deleteEmployee(String.valueOf(findEmployeeByProfileId(aLong).getId()));
        profileFeignClient.deleteProfile(String.valueOf(aLong));
    }

    @Override
    public EmployeeDTO findEmployeeByProfileId(Long aLong) {
        return employeeFeignClient.findByProfileID(String.valueOf(aLong));
    }

    @Override
    public ProfileDTO findProfileById(Long aLong) {
        return profileFeignClient.findById(String.valueOf(aLong));
    }
}