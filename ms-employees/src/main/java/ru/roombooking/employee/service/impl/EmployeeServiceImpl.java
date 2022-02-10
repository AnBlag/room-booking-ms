package ru.roombooking.employee.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final VCMapper<Employee, EmployeeDTO> myMapper;
    private final ProfileFeignClient profileFeignClient;
    private final JdbcTemplate jdbcTemplate;
    @Value("${sql.query.update.employee}")
    private String SQL_UPDATE_EMPLOYEE;

    @Override
    public EmployeeDTO save(EmployeeDTO model) {
        log.info("Сохранение сотрудника");
        return myMapper.toDTO(employeeRepository.save(myMapper.toModel(model)));
    }

    @Override
    public void restore(EmployeeDTO model) {
        log.info("Восстановление сотрудника");
        Employee employee = myMapper.toModel(model);
        jdbcTemplate.update(SQL_UPDATE_EMPLOYEE,
                employee.getId(),
                employee.getDepartmentId(),
                employee.getEmail(),
                employee.getIsActive(),
                employee.getMiddleName(),
                employee.getName(),
                employee.getPhone(),
                employee.getProfileId(),
                employee.getSurname());
        log.info("Восстановление сотрудника усешно завершено");
    }

    @Override
    public EmployeeDTO update(EmployeeDTO model, Long aLong) {
        log.info("Обновление сотрудника");
        model.setId(aLong);
        return myMapper.toDTO(employeeRepository.save(myMapper.toModel(model)));
    }

    @Override
    public List<EmployeeDTO> findAll() {
        log.info("Поиск всех сотрудников");
        return employeeRepository.findAll()
                .stream()
                .map(myMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO deleteById(Long aLong) {
        log.info("Удаление сотрудника по ID");
        EmployeeDTO employeeDTO = myMapper.toDTO(employeeRepository.findById(aLong)
                .orElseThrow(() -> new EmployeeBadRequestException("Не найден ID")));
        employeeRepository.deleteById(aLong);
        log.info("Удаление сотрудника по ID успешно завершено");
        return employeeDTO;
    }

    @Override
    public EmployeeDTO findByProfileID(Long profileID) {
        log.info("Поиск сотрудника по ID профиля");
        return myMapper.toDTO(employeeRepository.findByProfileId(profileID)
                .orElseThrow(() -> new EmployeeBadRequestException("Не найден ID")));
    }

    @Override
    public EmployeeDTO findByLogin(String login) {
        log.info("Поиск сотрудника по логину");
        try {
            return myMapper.toDTO(employeeRepository.findByProfileId(profileFeignClient.findByLogin(login).getId())
                    .orElseThrow(() -> new EmployeeBadRequestException("Не найден employee с таким profileId")));
        } catch (FeignException e) {
            throw new ProfileRequestException();
        }
    }

    @Override
    public Boolean isPresentByDepartmentId(Long aLong) {
        log.info("Проверка наличия сотрудников в департаменте " + aLong);
        return !employeeRepository.findAllByDepartmentId(aLong).isEmpty();
    }

    @Override
    public EmployeeDTO findById(Long aLong) {
        log.info("Поиск сотрудника по ID");
        return myMapper.toDTO(employeeRepository.findById(aLong)
                .orElseThrow(() -> new EmployeeBadRequestException("Не найден ID")));
    }

    @Override
    public ProfileDTO getProfileById(Long id) {
        log.info("Получение профиля по сотруднику");
        try {
            return profileFeignClient
                    .findById(String.valueOf(employeeRepository.findById(id).orElseThrow().getProfileId()));
        } catch (FeignException e) {
            throw new ProfileRequestException();
        }
    }
}