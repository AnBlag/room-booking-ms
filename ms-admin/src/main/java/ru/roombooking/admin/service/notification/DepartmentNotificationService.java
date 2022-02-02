package ru.roombooking.admin.service.notification;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.roombooking.admin.exception.DepartmentsDeleteException;
import ru.roombooking.admin.exception.DepartmentRequestException;
import ru.roombooking.admin.exception.DepartmentsSaveException;
import ru.roombooking.admin.exception.DepartmentsUpdateException;
import ru.roombooking.admin.feign.DepartmentFeignClient;
import ru.roombooking.admin.feign.EmployeeFeignClient;
import ru.roombooking.admin.model.dto.DepartmentDTO;
import ru.roombooking.admin.model.dto.DepartmentRequest;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentNotificationService {
    private final DepartmentFeignClient departmentFeignClient;
    private final EmployeeFeignClient employeeFeignClient;

    public List<DepartmentDTO> departments(String search) {
        log.info("Поиск департаментов");
        List<DepartmentDTO> departmentList;
        try {
            if (search != null) {
                log.info("Поиск департаментов по параметрам URL");
                departmentList = departmentFeignClient.getDepartmentListByURLParams(search);
            } else {
                log.info("Поиск всех департаментов");
                departmentList = departmentFeignClient.findAll();
            }
        } catch (FeignException e) {
            throw new DepartmentRequestException();
        }
        log.info("Поиск депортаментов завершен");
        return departmentList;
    }

    public List<DepartmentDTO> findDepartments(DepartmentDTO findDepartment) {
        try {
            log.info("Поиск департаментов по параметрам");
            return departmentFeignClient.getDepartmentListByDepartmentParams(findDepartment);
        } catch (FeignException e) {
            throw new DepartmentRequestException();
        }
    }

    public void updateDepartments(DepartmentRequest departmentRequest) {
        try {
            log.info("Обновление департаментов");
            departmentFeignClient.batchUpdateDepartment(getDepartmentListFromParams(String.valueOf(departmentRequest.getId()),
                    departmentRequest.getNameDepartment(),
                    departmentRequest.getPosition()));
            log.info("Успешное обновление департаментов");
        } catch (FeignException e) {
            throw new DepartmentsUpdateException("Ошибка обновления департаментов");
        }
    }

    public String askToDeleteDepartment(String id) {
        log.info("Запрос на удаление департамента");
        return getMessageForDeleteDepartmentPage(Long.parseLong(id));
    }

    public void deleteDepartment(String id) {
        try {
            log.info("Удаление департамента");
            departmentFeignClient.deleteDepartment(id);
            log.info("Успешное удаление департамента");
        } catch (FeignException e) {
            throw new DepartmentsDeleteException();
        }
    }

    public void saveNewDepartment(DepartmentDTO department) {
        try {
            log.info("Добавление нового департамента");
            departmentFeignClient.saveDepartment(department);
            log.info("Департамент успешно добавлен");
        } catch (FeignException e) {
            throw new DepartmentsSaveException();
        }
    }

    private List<DepartmentDTO> getDepartmentListFromParams(String id,
                                                            String departmentName,
                                                            String position) {
        String[] idMas = id.split(",");
        String[] departmentNameMas = departmentName.split(",");
        String[] positionMas = position.split(",");

        List<DepartmentDTO> departmentList = new ArrayList<>();
        for (int i = 0; i < idMas.length; i++) {
            departmentList.add(
                    DepartmentDTO.builder()
                            .id(Long.parseLong(idMas[i]))
                            .nameDepartment(departmentNameMas[i])
                            .position(positionMas[i])
                            .build()
            );
        }
        return departmentList;
    }

    private String getMessageForDeleteDepartmentPage(Long id) {
        try {
            String departmentName = departmentFeignClient.findById(String.valueOf(id)).getNameDepartment();
            if (employeeFeignClient.isPresentByDepartmentId(String.valueOf(id)))
                return "В департаменте " + departmentName + " остались сотрудники, удалить его?";
            else return "Удалить департамент " + departmentName + "?";
        } catch (FeignException e) {
            throw new DepartmentRequestException();
        }
    }
}