package ru.roombooking.front.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.front.exception.EmployeeRequestException;
import ru.roombooking.front.exception.ProfileRequestException;
import ru.roombooking.front.feign.EmployeeFeignClient;
import ru.roombooking.front.feign.ProfileFeignClient;
import ru.roombooking.front.model.dto.EmployeeDTO;
import ru.roombooking.front.model.dto.ProfileDTO;
import ru.roombooking.front.service.EmployeeAndProfileService;

@Service
@RequiredArgsConstructor
public class EmployeeAndProfileServiceImpl implements EmployeeAndProfileService {
    private final EmployeeFeignClient employeeFeignClient;
    private final ProfileFeignClient profileFeignClient;

    @Override
    @Transactional
    public void update(EmployeeDTO employeeDTO, ProfileDTO profile) {

        try {
            employeeFeignClient.saveEmployee(employeeDTO);
        } catch (FeignException e) {
            throw new EmployeeRequestException();
        }

        try {
            profileFeignClient.saveProfile(profile);
        } catch (FeignException e) {
            throw new ProfileRequestException();
        }
    }

    @Override
    public ProfileDTO findProfileById(Long aLong) {

        try {
            return profileFeignClient.findById(String.valueOf(aLong));
        } catch (FeignException e) {
            throw new ProfileRequestException();
        }
    }

    @Override
    public EmployeeDTO findByLogin(String login) {

        try {
            return employeeFeignClient.findByLogin(login);
        } catch (FeignException e) {
            throw new EmployeeRequestException();
        }
    }
}