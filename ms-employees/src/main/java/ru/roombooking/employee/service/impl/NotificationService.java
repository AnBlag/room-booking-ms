package ru.roombooking.employee.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.employee.config.search.SearchByURLParams;
import ru.roombooking.employee.exception.EmployeeDeleteException;
import ru.roombooking.employee.exception.EmployeeSaveException;
import ru.roombooking.employee.exception.EmployeeUpdateException;
import ru.roombooking.employee.model.EmployeeView;
import ru.roombooking.employee.model.dto.ProfileDTO;
import ru.roombooking.employee.model.dto.EmployeeDTO;
import ru.roombooking.employee.repository.SearchCriteriaViewRepository;
import ru.roombooking.employee.service.EmployeeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final EmployeeService employeeService;
    private final SearchCriteriaViewRepository<EmployeeView> searchCriteriaViewRepository;
    private final SearchByURLParams searchByURLParams;

    @Transactional(readOnly = true)
    public List<EmployeeDTO> findAll() {
        return employeeService.findAll();
    }

    @Transactional(rollbackFor = EmployeeSaveException.class)
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        return employeeService.save(employeeDTO);
    }

    @Transactional(rollbackFor = EmployeeUpdateException.class)
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, String id) {
        return employeeService.update(employeeDTO, Long.parseLong(id));
    }

    @Transactional(rollbackFor = EmployeeDeleteException.class)
    public EmployeeDTO deleteEmployee(String id) {
        return employeeService.deleteById(Long.parseLong(id));
    }

    @Transactional(readOnly = true)
    public EmployeeDTO getEmployee(String login) {
        return employeeService.findByLogin(login);
    }

    @Transactional(readOnly = true)
    public EmployeeDTO findByProfileID(String profileId) {
        return employeeService.findByProfileID(Long.parseLong(profileId));
    }

    @Transactional(readOnly = true)
    public EmployeeDTO findByLogin(String login) {
        return employeeService.findByLogin(login);
    }

    @Transactional(readOnly = true)
    public ProfileDTO getProfileById(String id) {
        return employeeService.getProfileById(Long.parseLong(id));
    }

    @Transactional(readOnly = true)
    public EmployeeDTO findById(String id) {
        return employeeService.findById(Long.parseLong(id));
    }

    @Transactional(readOnly = true)
    public Boolean isPresentByDepartmentId(String departmentId) {
        return employeeService.isPresentByDepartmentId(Long.parseLong(departmentId));
    }

    @Transactional(readOnly = true)
    public List<EmployeeView> getEmployeeViewListByURLParams(String search) {
        return searchCriteriaViewRepository.search(searchByURLParams.getParamsFromSearch(search));
    }

    @Transactional(readOnly = true)
    public List<EmployeeView> getEmployeeViewListByEmployeeViewParams(EmployeeView employeeView) {
        return searchCriteriaViewRepository.search(searchByURLParams.getParamsFromProfileView(employeeView));
    }
}