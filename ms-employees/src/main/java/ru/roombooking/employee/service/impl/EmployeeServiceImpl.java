package ru.roombooking.employee.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.roombooking.employee.exception.DepartmentRequestException;
import ru.roombooking.employee.exception.EmployeeBadRequestException;
import ru.roombooking.employee.exception.ProfileRequestException;
import ru.roombooking.employee.feign.DepartmentFeignClient;
import ru.roombooking.employee.feign.ProfileFeignClient;
import ru.roombooking.employee.mapper.VCMapper;
import ru.roombooking.employee.model.Employee;
import ru.roombooking.employee.model.dto.ProfileDTO;
import ru.roombooking.employee.model.dto.EmployeeDTO;
import ru.roombooking.employee.repository.EmployeeRepository;
import ru.roombooking.employee.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final VCMapper<Employee, EmployeeDTO> myMapper;
    private final ProfileFeignClient profileFeignClient;
    private final DepartmentFeignClient departmentFeignClient;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public EmployeeDTO save(EmployeeDTO model) {
        return myMapper.toDTO(employeeRepository.save(myMapper.toModel(model)));
    }

    @Override
    public void restore(EmployeeDTO model) {
        Employee employee = myMapper.toModel(model);
        jdbcTemplate.update("insert into employee (id, department_id, email, is_active, middle_name, name, phone, profile_id, surname) values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                employee.getId(),
                employee.getDepartmentId(),
                employee.getEmail(),
                employee.getIsActive(),
                employee.getMiddleName(),
                employee.getName(),
                employee.getPhone(),
                employee.getProfileId(),
                employee.getSurname());
    }

    @Override
    public EmployeeDTO update(EmployeeDTO model, Long aLong) {
        model.setId(aLong);
        return myMapper.toDTO(employeeRepository.save(myMapper.toModel(model)));
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(myMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO deleteById(Long aLong) {
        EmployeeDTO employeeDTO = myMapper.toDTO(employeeRepository.findById(aLong)
                .orElseThrow(() -> new EmployeeBadRequestException("Не найден ID")));
        employeeRepository.deleteById(aLong);
        return employeeDTO;
    }

    @Override
    public EmployeeDTO findByProfileID(Long profileID) {
        return myMapper.toDTO(employeeRepository.findByProfileId(profileID)
                .orElseThrow(() -> new EmployeeBadRequestException("Не найден ID")));
    }

    @Override
    public EmployeeDTO findByLogin(String login) {
        try {
            return myMapper.toDTO(employeeRepository.findByProfileId(profileFeignClient.findByLogin(login).getId())
                    .orElseThrow(() -> new EmployeeBadRequestException("Не найден employee с таким profileId")));
        } catch (FeignException e) {
            throw new ProfileRequestException();
        }
    }

    @Override
    public Boolean isPresentByDepartmentId(Long aLong) {
        return !employeeRepository.findAllByDepartmentId(aLong).isEmpty();
    }

    @Override
    public EmployeeDTO findById(Long aLong) {
        return myMapper.toDTO(employeeRepository.findById(aLong)
                .orElseThrow(() -> new EmployeeBadRequestException("Не найден ID")));
    }

    @Override
    public ProfileDTO getProfileById(Long id) {
        try {
            return profileFeignClient
                    .findById(String.valueOf(employeeRepository.findById(id).orElseThrow().getProfileId()));
        } catch (FeignException e) {
            throw new ProfileRequestException();
        }
    }

    private Employee toEmployee(EmployeeDTO model) {
        Employee employee = myMapper.toModel(model);

        try {
            employee.setDepartmentId(departmentFeignClient
                    .findById(String.valueOf(model.getDepartmentId())).getId());
        } catch (FeignException e) {
            throw new DepartmentRequestException();
        }

        try {
            employee.setProfileId(profileFeignClient.findById(String.valueOf(model.getProfileId())).getId());
        } catch (FeignException e) {
            throw new ProfileRequestException();
        }

        return employee;
    }
}