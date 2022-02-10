package ru.roombooking.departments.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.departments.exception.DepartmentBadRequestException;
import ru.roombooking.departments.model.Department;
import ru.roombooking.departments.repository.DepartmentRepository;
import ru.roombooking.departments.service.DepartmentService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final JdbcTemplate jdbcTemplate;
    @Value("${sql.query.batch-update}")
    private String SQL_BATCH_UPDATE;

    @Override
    public Department save(Department model) {
        log.info("Сохранение департамента");
        return departmentRepository.save(model);
    }

    @Override
    public Department update(Department model, Long id) {
        log.info("Обновление департамента");
        model.setId(id);
        return departmentRepository.save(model);
    }

    @Override
    public List<Department> findAll() {
        log.info("Поиск всех департаментов");
        return departmentRepository.findAll();
    }

    @Override
    public Department deleteById(Long aLong) {
        log.info("Удаление департамента по ID");
        Department department = departmentRepository.findById(aLong)
                .orElseThrow(() -> new DepartmentBadRequestException("Не найден ID"));
        departmentRepository.deleteById(aLong);
        log.info("Удаление департамента по ID успешно завершено");
        return department;
    }

    @Override
    public Department findById(Long aLong) {
        log.info("Поиск департамента по ID");
        return departmentRepository.findById(aLong)
                .orElseThrow(() -> new DepartmentBadRequestException("Не найден ID"));
    }

    @Transactional
    @Override
    public void batchUpdateDepartment(List<Department> departmentList) {
        log.info("Обновление всех департаментов");
        jdbcTemplate.batchUpdate(SQL_BATCH_UPDATE,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, departmentList.get(i).getNameDepartment());
                        ps.setString(2, departmentList.get(i).getPosition());
                        ps.setLong(3, departmentList.get(i).getId());
                    }

                    @Override
                    public int getBatchSize() {
                        return departmentList.size();
                    }
                });
    }
}