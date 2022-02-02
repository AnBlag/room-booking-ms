package ru.roombooking.admin.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.roombooking.admin.exception.*;
import ru.roombooking.admin.feign.EmployeeFeignClient;
import ru.roombooking.admin.feign.ProfileFeignClient;
import ru.roombooking.admin.model.dto.EmployeeDTO;
import ru.roombooking.admin.model.dto.ProfileDTO;
import ru.roombooking.admin.service.EmployeeAndProfileService;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeAndProfileServiceImpl implements EmployeeAndProfileService {
    private final EmployeeFeignClient employeeFeignClient;
    private final ProfileFeignClient profileFeignClient;

    // FIXME: 31.01.2022 вопрос с синхронным update и delete
    @Override
    public void update(EmployeeDTO employeeDTO, ProfileDTO profile) {
        log.info("Обновление данных о пользователе");

        ProfileDTO tempProfile;

        try {
            tempProfile = profileFeignClient.findById(String.valueOf(profile.getId()));
            profileFeignClient.saveProfile(profile);
            log.info("Успешное обновление профиля");
        } catch (FeignException e) {
            throw new ProfileUpdateException();
        }

        try {
            employeeFeignClient.saveEmployee(employeeDTO);
            log.info("Успешное обновление данных о сотруднике");
        } catch (FeignException e) {
            try {
                profileFeignClient.saveProfile(tempProfile);
                log.info("Откат обновления профиля");
            } catch (FeignException exception) {
                throw new ProfileUpdateException();
            }
            throw new EmployeeUpdateException();
        }
        log.info("Успешное обновление данных о пользователе");
    }

    @Override
    public void deleteByProfileId(Long aLong) {
        log.info("Удаление данных о пользователе");

        EmployeeDTO tempEmployee;

        try {
            tempEmployee = employeeFeignClient.deleteEmployee(String.valueOf(findEmployeeByProfileId(aLong).getId()));
            log.info("Успешное удаление данных о сотруднике");
        } catch (FeignException e) {
            throw new EmployeeDeleteException();
        }

        try {
            profileFeignClient.deleteProfile(String.valueOf(aLong));
            log.info("Успешное удаление профиля");
        } catch (FeignException e) {
            try {
                employeeFeignClient.restoreEmployee(tempEmployee);
                log.info("Успешное восстановление данных о сотруднике");
            } catch (FeignException exception) {
                throw new EmployeeSaveException();
            }
            throw new ProfileDeleteException();
        }

        log.info("Успешное удаление данных о пользователе");
    }

    @Override
    public EmployeeDTO findEmployeeByProfileId(Long aLong) {
        log.info("Поиск сотрудника по profileId");
        try {
            return employeeFeignClient.findByProfileID(String.valueOf(aLong));
        } catch (FeignException e) {
            throw new EmployeeRequestException();
        }
    }

    @Override
    public ProfileDTO findProfileById(Long aLong) {
        return profileFeignClient.findById(String.valueOf(aLong));
    }
}