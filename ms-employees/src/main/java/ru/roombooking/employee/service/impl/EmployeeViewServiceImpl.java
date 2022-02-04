package ru.roombooking.employee.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.employee.model.Employee;
import ru.roombooking.employee.model.dto.ProfileDTO;
import ru.roombooking.employee.model.EmployeeView;
import ru.roombooking.employee.repository.EmployeeViewRepository;
import ru.roombooking.employee.service.RoomService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeViewServiceImpl implements RoomService<EmployeeView, Long> {
    private final JdbcTemplate jdbcTemplate;
    private final EmployeeViewRepository employeeViewRepository;
    @Value("${sql.query.batch-update.employee}")
    private String batchUpdateEmployeeQuery;
    @Value("${sql.query.batch-update.profile}")
    private String batchUpdateProfileQuery;

    @Transactional
    @Override
    public EmployeeView save(EmployeeView model) {
        log.info("Сохранение сотрудника");
        return employeeViewRepository.save(model);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeView> findAll() {
        log.info("Поиск сотрудников");
        return employeeViewRepository.findAll();
    }

    @Transactional
    public void batchUpdateProfileAndEmployee(List<EmployeeView> employeeViewList) {
        log.info("Сохранение сотрудников и профилей");
        List<Employee> employeeList = employeeViewList.stream()
                .map(this::toEmployee)
                .collect(Collectors.toList());

        List<ProfileDTO> profileList = employeeViewList.stream()
                .map(this::toProfile)
                .collect(Collectors.toList());

        batchUpdateProfileAndEmployee(profileList, employeeList);
        log.info("Сохранение сотрудников и профилей успешно завершено");
    }

    private Employee toEmployee(EmployeeView employeeView) {
        return Employee.builder()
                .name(employeeView.getName())
                .middleName(employeeView.getMiddleName())
                .surname(employeeView.getSurname())
                .phone(employeeView.getPhone())
                .email(employeeView.getEmail())
                .profileId(employeeView.getId())
                .build();
    }

    private ProfileDTO toProfile(EmployeeView employeeView) {
        return ProfileDTO.builder()
                .id(employeeView.getId())
                .accountNonLocked(employeeView.getBanned())
                .build();
    }

    private void batchUpdateProfileAndEmployee(List<ProfileDTO> profileList, List<Employee> employeeList) {
        jdbcTemplate.batchUpdate(batchUpdateEmployeeQuery,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, employeeList.get(i).getEmail());
                        ps.setString(2, employeeList.get(i).getName());
                        ps.setString(3, employeeList.get(i).getMiddleName());
                        ps.setString(4, employeeList.get(i).getSurname());
                        ps.setString(5, employeeList.get(i).getPhone());
                        ps.setLong(6, employeeList.get(i).getProfileId());
                    }

                    @Override
                    public int getBatchSize() {
                        return employeeList.size();
                    }
                });
        log.info("Сохранение сотрудников успешно завершено");

        jdbcTemplate.batchUpdate(batchUpdateProfileQuery,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setBoolean(1, profileList.get(i).getAccountNonLocked());
                        ps.setLong(2, profileList.get(i).getId());
                    }

                    @Override
                    public int getBatchSize() {
                        return profileList.size();
                    }
                });
        log.info("Сохранение профилей успешно завершено");
    }
}