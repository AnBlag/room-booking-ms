package ru.roombooking.admin.service.notification;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.roombooking.admin.exception.*;
import ru.roombooking.admin.feign.DepartmentFeignClient;
import ru.roombooking.admin.feign.EmployeeViewFeignClient;
import ru.roombooking.admin.model.dto.DepartmentDTO;
import ru.roombooking.admin.model.EmployeeView;
import ru.roombooking.admin.model.dto.ProfileDTO;
import ru.roombooking.admin.model.dto.EmployeeDTO;
import ru.roombooking.admin.model.dto.EmployeeEditDTO;
import ru.roombooking.admin.model.dto.EmployeeRequest;
import ru.roombooking.admin.service.EmployeeAndProfileService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeNotificationService {
    private final EmployeeAndProfileService employeeAndProfileService;
    private final DepartmentFeignClient departmentFeignClient;
    private final EmployeeViewFeignClient employeeViewFeignClient;

    public void updateUsers(EmployeeRequest employeeRequest) {
        try {
            log.info("Обновление пользователей");
            employeeViewFeignClient.batchUpdateProfileAndEmployee(getEmployeeViewListFromParams(String.valueOf(employeeRequest.getId()),
                    employeeRequest.getName(),
                    employeeRequest.getSurname(),
                    employeeRequest.getMiddleName(),
                    employeeRequest.getPhone(),
                    employeeRequest.getEmail(),
                    employeeRequest.getBanned()));
            log.info("Обновление пользователей успешно завершено");
        } catch (FeignException e) {
            throw new EmployeeUpdateException();
        }
    }

    public List<EmployeeView> getByParamPage(String search) {
        log.info("Поиск сотрудников");
        List<EmployeeView> list;
        try {
            if (search != null) {
                log.info("Поиск сотрудников по URL");
                list = employeeViewFeignClient.getEmployeeViewListByURLParams(search);
            } else {
                log.info("Поиск всех сотрудников");
                list = employeeViewFeignClient.findAll();
            }
            log.info("Поиск сотрудников успешно завершен");
            return list;
        } catch (FeignException e) {
            throw new EmployeeViewRequestException();
        }
    }

    public List<EmployeeView> findByParamPage(@RequestBody EmployeeView employeeView) {
        try {
            log.info("Поиск сотрудников по параметрам");
            return employeeViewFeignClient.getEmployeeViewListByEmployeeViewParams(employeeView);
        } catch (FeignException e) {
            throw new EmployeeViewRequestException();
        }
    }

    public EmployeeEditDTO editEmployee(String id) {
        log.info("Редактирование сотрудников");
        EmployeeDTO employeeDTO = employeeAndProfileService.findEmployeeByProfileId(Long.parseLong(id));
        ProfileDTO profile = employeeAndProfileService.findProfileById(Long.parseLong(id));
        List<DepartmentDTO> departmentList;
        try {
            log.info("Поиск всех департаментов");
            departmentList = departmentFeignClient.findAll();
        } catch (FeignException e) {
            throw new DepartmentRequestException();
        }
        log.info("Редактирование сотрудников успешно завершено");
        return new EmployeeEditDTO(employeeDTO, profile, departmentList);
    }

    public void saveEmployee(String id,
                             EmployeeDTO employeeDTO,
                             ProfileDTO profile) {
        log.info("Сохранение сотрудников");
        try {
            saveEmployeeFromPageData(Long.parseLong(id), employeeDTO, profile);
            log.info("Сохранение сотрудников успешно завершено");
        } catch (Exception e) {
            throw new UsersSaveException();
        }
    }

    public void deleteEmployee(String id) {
        log.info("Удаление сотрудников");
        try {
            employeeAndProfileService.deleteByProfileId(Long.parseLong(id));
            log.info("Удаление сотрудников успешно завершено");
        } catch (Exception e) {
            throw new UsersDeleteException();
        }
    }

    private List<EmployeeView> getEmployeeViewListFromParams(String id,
                                                             String name,
                                                             String surname,
                                                             String middleName,
                                                             String phone,
                                                             String email,
                                                             String banned) {
        String[] idMas = id.split(",");
        String[] nameMas = name.split(",");
        String[] surnameMas = surname.split(",");
        String[] middleNameMas = middleName.split(",");
        String[] phoneMas = phone.split(",");
        String[] emailMas = email.split(",");
        String[] bannedMas = banned.split(",");

        List<EmployeeView> employeeViewList = new ArrayList<>();
        for (int i = 0; i < idMas.length; i++) {
            employeeViewList.add(
                    EmployeeView.builder()
                            .id(Long.parseLong(idMas[i]))
                            .name(nameMas[i])
                            .surname(surnameMas[i])
                            .middleName(middleNameMas[i])
                            .phone(phoneMas[i])
                            .email(emailMas[i])
                            .banned(Boolean.parseBoolean(bannedMas[i]))
                            .build()
            );
        }
        return employeeViewList;
    }

    private void saveEmployeeFromPageData(Long id, EmployeeDTO employeeDTO, ProfileDTO profile) {
        EmployeeDTO tempEmployeeDTO = employeeAndProfileService.findEmployeeByProfileId(id);
        ProfileDTO tempProfile = employeeAndProfileService.findProfileById(id);
        profile.setId(id);
        profile.setPassword(tempProfile.getPassword());
        employeeDTO.setIsActive(tempEmployeeDTO.getIsActive());
        employeeDTO.setId(tempEmployeeDTO.getId());
        employeeDTO.setProfileId(id);
        employeeAndProfileService.update(employeeDTO, profile);
    }
}