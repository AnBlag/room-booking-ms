package ru.roombooking.front.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.roombooking.front.exception.EmployeeRequestException;
import ru.roombooking.front.exception.ProfileRequestException;
import ru.roombooking.front.feign.EmployeeFeignClient;
import ru.roombooking.front.feign.ProfileFeignClient;
import ru.roombooking.front.model.dto.EmployeeDTO;
import ru.roombooking.front.model.dto.ProfileDTO;
import ru.roombooking.front.service.EmployeeAndProfileService;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeAndProfileServiceImpl implements EmployeeAndProfileService {
    private final EmployeeFeignClient employeeFeignClient;
    private final ProfileFeignClient profileFeignClient;

    @Override
    public void update(EmployeeDTO employeeDTO, ProfileDTO profile) {
        log.info("Обновление данных о пользователе");
        ProfileDTO tempProfile;

        try {
            tempProfile = profileFeignClient.findById(String.valueOf(profile.getId()));
            profileFeignClient.saveProfile(profile);
            log.info("Успешное обновление профиля");
        } catch (FeignException e) {
            throw new ProfileRequestException();
        }

        try {
            employeeFeignClient.saveEmployee(employeeDTO);
            log.info("Успешное обновление данных о сотруднике");
        } catch (FeignException e) {
            try {
                profileFeignClient.saveProfile(tempProfile);
                log.info("Успешный откат обновления профиля");
            } catch (FeignException exception) {
                throw new ProfileRequestException();
            }
            throw new EmployeeRequestException();
        }
        log.info("Успешное обновление данных о пользователе");
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