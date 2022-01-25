package ru.roombooking.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.roombooking.employee.model.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(nativeQuery = true,
            value = "select * from employee where department_id=?1")
    List<Employee> findAllByDepartmentId(Long aLong);

    @Query(nativeQuery = true,
            value = "select * from employee where department_id=?1 and profile_id=?2")
    Optional<Employee> findByDepartmentIdAndProfileId(Long depId, Long profId);

    @Query(nativeQuery = true,
            value = "select * from employee where department_id=?")
    Optional<Employee> findByDepartmentId(Long depId);

    @Query(nativeQuery = true, value = "select * from profile inner join employee e on profile.id = e.profile_id " +
            "where login=?1")
    Optional<Employee> findByLogin(String login);

    @Query(nativeQuery = true,
            value = "select * from employee where profile_id=?")
    Optional<Employee> findByProfileId(Long profileID);
}