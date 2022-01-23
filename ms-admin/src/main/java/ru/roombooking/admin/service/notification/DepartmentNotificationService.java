package ru.roombooking.admin.service.notification;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.roombooking.admin.exception.DepartmentsDeleteException;
import ru.roombooking.admin.exception.DepartmentBadRequestException;
import ru.roombooking.admin.exception.SaveDepartmentsException;
import ru.roombooking.admin.exception.UpdateDepartmentsException;
import ru.roombooking.admin.feign.DepartmentFeignClient;
import ru.roombooking.admin.feign.EmployeeFeignClient;
import ru.roombooking.admin.model.Department;
import ru.roombooking.admin.model.dto.DepartmentRequest;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentNotificationService {
    private final DepartmentFeignClient departmentFeignClient;
    private final EmployeeFeignClient employeeFeignClient;


    public List<Department> departments(String search) {
        List<Department> departmentList;
        if (search != null) {
            departmentList = departmentFeignClient.getDepartmentListByURLParams(search);
        }
        else {
            try {
                departmentList = departmentFeignClient.findAll();
            } catch (FeignException e) {
                throw new DepartmentBadRequestException();
            }
        }
        return departmentList;
    }

    public List<Department> findDepartments(Department findDepartment) {
        return departmentFeignClient.getDepartmentListByDepartmentParams(findDepartment);
    }

    public void updateDepartments(DepartmentRequest departmentRequest) {
        try {
            departmentFeignClient.batchUpdateDepartment(getDepartmentListFromParams(String.valueOf(departmentRequest.getId()),
                    departmentRequest.getNameDepartment(),
                    departmentRequest.getPosition()));
        } catch (FeignException e) {
            throw new UpdateDepartmentsException("Ошибка обновления департаментов");
        }
    }

    public String askDeleteDepartment(String id) {
        return getMessageForDeleteDepartmentPage(Long.parseLong(id));
    }

    public void deleteDepartment(String id) {
        try {
            departmentFeignClient.deleteDepartment(id);
        } catch (FeignException e) {
            throw new DepartmentsDeleteException();
        }
    }

    public void saveNewDepartment(Department department) {
        try {
            departmentFeignClient.saveDepartment(department);
        } catch (FeignException e) {
            throw new SaveDepartmentsException();
        }
    }

    private List<Department> getDepartmentListFromParams(String id,
                                                         String departmentName,
                                                         String position) {
        String[] idMas = id.split(",");
        String[] departmentNameMas = departmentName.split(",");
        String[] positionMas = position.split(",");

        List<Department> departmentList = new ArrayList<>();
        for (int i=0; i<idMas.length; i++) {
            departmentList.add(
                    Department.builder()
                            .id(Long.parseLong(idMas[i]))
                            .nameDepartment(departmentNameMas[i])
                            .position(positionMas[i])
                            .build()
            );
        }
        return departmentList;
    }

    private String getMessageForDeleteDepartmentPage (Long id) {
        try {
            String departmentName = departmentFeignClient.findById(String.valueOf(id)).getNameDepartment();
            if (employeeFeignClient.isPresentByDepartmentId(String.valueOf(id)))
                return "В департаменте " + departmentName + " остались сотрудники, удалить его?";
            else return "Удалить департамент " + departmentName + "?";
        } catch (FeignException e) {
            throw new DepartmentBadRequestException();
        }

    }

}
