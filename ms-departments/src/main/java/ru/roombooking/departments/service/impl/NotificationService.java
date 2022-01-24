package ru.roombooking.departments.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.departments.config.search.SearchByParams;
import ru.roombooking.departments.exception.DepartmentDeleteException;
import ru.roombooking.departments.exception.DepartmentSaveException;
import ru.roombooking.departments.exception.DepartmentUpdateException;
import ru.roombooking.departments.model.Department;
import ru.roombooking.departments.repository.SearchCriteriaViewRepository;
import ru.roombooking.departments.service.DepartmentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final DepartmentService departmentService;
    private final SearchCriteriaViewRepository<Department> searchCriteriaViewRepository;
    private final SearchByParams searchByParams;

    @Transactional(readOnly = true)
    public List<Department> findAll() {
        return departmentService.findAll();
    }

    @Transactional(rollbackFor = DepartmentSaveException.class)
    public Department saveDepartment(Department department) {
        return departmentService.save(department);
    }

    @Transactional(rollbackFor = DepartmentUpdateException.class)
    public Department updateDepartment(Department department, String id) {
        return departmentService.update(department, Long.parseLong(id));
    }

    @Transactional(rollbackFor = DepartmentDeleteException.class)
    public Department deleteDepartment(String id) {
        return departmentService.deleteById(Long.parseLong(id));
    }

    @Transactional(readOnly = true)
    public Department findById(String id) {
        return departmentService.findById(Long.parseLong(id));
    }

    public void batchUpdateDepartment(List<Department> departmentList) {
        departmentService.batchUpdateDepartment(departmentList);
    }

    @Transactional(readOnly = true)
    public List<Department> getDepartmentListByURLParams(String search) {
        return searchCriteriaViewRepository.search(searchByParams.getParamsFromSearch(search));
    }

    @Transactional(readOnly = true)
    public List<Department> getDepartmentListByDepartmentParams(Department departmentParams) {
        return searchCriteriaViewRepository.search(searchByParams.getParamsFromDepartment(departmentParams));
    }
}