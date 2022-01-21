package ru.roombooking.front.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.front.exception.EmployeeBadRequestException;
import ru.roombooking.front.exception.ProfileBadRequestException;
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
        }
        catch (FeignException e) {
            throw new EmployeeBadRequestException();
        }

        try {
            profileFeignClient.saveProfile(profile);
        }
        catch (FeignException e) {
            throw new ProfileBadRequestException();
        }
    }

    /*@Override
    @Transactional
    public void deleteByProfileId(Long aLong) {
        employeeService.deleteById(findEmployeeByProfileId(aLong).getId());
        profileService.deleteById(aLong);
    }*/

    /*@Override
    public EmployeeDTO findEmployeeByProfileId(Long aLong) {
        return employeeService.findEmployeeByProfileId(aLong);
    }*/

    @Override
    public ProfileDTO findProfileById(Long aLong) {

        try {
            return profileFeignClient.findById(String.valueOf(aLong));
        } catch (FeignException e) {
            throw new ProfileBadRequestException();
        }
    }

    @Override
    public EmployeeDTO findByLogin(String login) {

        try {
            return employeeFeignClient.findByLogin(login);
        } catch (FeignException e){
            throw new EmployeeBadRequestException();
        }
    }




}
