package ru.roombooking.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.roombooking.employee.model.EmployeeView;

import java.util.List;

@Repository
public interface EmployeeViewRepository extends JpaRepository<EmployeeView, Long> {
    List<EmployeeView> findAllBySurname(String surname);
    List<EmployeeView> findAllByName(String name);
}
