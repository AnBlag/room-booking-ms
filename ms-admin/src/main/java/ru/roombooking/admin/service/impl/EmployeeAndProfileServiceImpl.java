package ru.roombooking.admin.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.admin.exception.UpdateUsersException;
import ru.roombooking.admin.feign.EmployeeFeignClient;
import ru.roombooking.admin.feign.ProfileFeignClient;
import ru.roombooking.admin.model.Profile;
import ru.roombooking.admin.model.dto.EmployeeDTO;
import ru.roombooking.admin.service.EmployeeAndProfileService;


@Service
@RequiredArgsConstructor
public class EmployeeAndProfileServiceImpl implements EmployeeAndProfileService {
    private final EmployeeFeignClient employeeFeignClient;
    private final ProfileFeignClient profileFeignClient;

    @Override
    @Transactional
    public void update(EmployeeDTO employeeDTO, Profile profile) {
        try {
            employeeFeignClient.saveEmployee(employeeDTO);
            profileFeignClient.saveProfile(profile);
        } catch (FeignException e) {
            throw new UpdateUsersException();
        }

    }

    @Override
    @Transactional
    public void deleteByProfileId(Long aLong) {
        employeeFeignClient.deleteEmployee(String.valueOf(findEmployeeByProfileId(aLong).getId()));
        profileFeignClient.deleteProfile(String.valueOf(aLong));
    }

    @Override
    public EmployeeDTO findEmployeeByProfileId(Long aLong) {
        return employeeFeignClient.findByProfileID(String.valueOf(aLong));
    }

    @Override
    public Profile findProfileById(Long aLong) {
        return profileFeignClient.findById(String.valueOf(aLong));
    }


    @Override
    public EmployeeDTO findByLogin(String login) {
        return employeeFeignClient.findByLogin(login);
    }




}
