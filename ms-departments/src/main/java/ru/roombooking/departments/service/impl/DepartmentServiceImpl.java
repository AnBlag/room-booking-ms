package ru.roombooking.departments.service.impl;

import lombok.RequiredArgsConstructor;
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
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final JdbcTemplate jdbcTemplate;
    @Value("${sql.query.batch-update}")
    private String batchUpdateQuery;

    @Override
    public Department save(Department model) {
        return departmentRepository.save(model);
    }

    @Override
    public Department update(Department model, Long id) {
        model.setId(id);
        return departmentRepository.save(model);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department deleteById(Long aLong) {
        Department department = departmentRepository.findById(aLong)
                .orElseThrow(() -> new DepartmentBadRequestException("Не найден ID"));
        departmentRepository.deleteById(aLong);
        return department;
    }

    @Override
    public Department findById(Long aLong) {
        return departmentRepository.findById(aLong)
                .orElseThrow(() -> new DepartmentBadRequestException("Не найден ID"));
    }

    @Transactional
    @Override
    public void batchUpdateDepartment(List<Department> departmentList) {
        jdbcTemplate.batchUpdate("" + batchUpdateQuery,
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