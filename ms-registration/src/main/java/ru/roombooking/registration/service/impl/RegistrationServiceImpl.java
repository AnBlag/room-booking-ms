package ru.roombooking.registration.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.roombooking.registration.exception.EmployeeSaveException;
import ru.roombooking.registration.exception.ProfileRequestException;
import ru.roombooking.registration.exception.ProfileSaveException;
import ru.roombooking.registration.feign.EmployeeFeignClient;
import ru.roombooking.registration.feign.ProfileFeignClient;
import ru.roombooking.registration.mapper.VCMapper;
import ru.roombooking.registration.model.dto.EmployeeDTO;
import ru.roombooking.registration.model.dto.ProfileDTO;
import ru.roombooking.registration.model.dto.RegistrationDTO;
import ru.roombooking.registration.service.RegistrationService;

@RequiredArgsConstructor
@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {
    private final EmployeeFeignClient employeeFeignClient;
    private final ProfileFeignClient profileFeignClient;
    private final VCMapper<EmployeeDTO, RegistrationDTO> myEmployeeMapper;
    private final VCMapper<ProfileDTO, RegistrationDTO> myProfileMapper;

    // FIXME: 31.01.2022 костыль, если возникает ошибка при сохранении employee, то удаляем новый профиль
    @Override
    public void saveEmployeeAndProfile(RegistrationDTO model) {
        ProfileDTO tempProfile;

        try {
            log.info("Создание профиля сотрудника");
            tempProfile = profileFeignClient.saveProfile(myProfileMapper.toModel(model));
        } catch (FeignException e) {
            throw new ProfileSaveException();
        }

        try {
            log.info("Создание сотрудника");
            model.setProfileId(tempProfile.getId());
            employeeFeignClient.saveEmployee(myEmployeeMapper.toModel(model));
        } catch (FeignException e) {
            try {
                log.info("Ошибка создания сотрудника, удаление профиля сотрудника");
                profileFeignClient.deleteByProfile(tempProfile);
            } catch (FeignException exception) {
                throw new ProfileRequestException();
            }
            throw new EmployeeSaveException();
        }
        log.info("Создание сотрудника и профиля успешно завершено");
    }

    @Override
    public boolean doesUserExist(RegistrationDTO model) {
        log.info("Проверка существования профиля");
        return profileFeignClient.doesProfileExist(model.getLogin());
    }
}