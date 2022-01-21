package ru.roombooking.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.roombooking.history.model.HistoryRecordTableEmployee;

@Repository
public interface HistoryRecordTableEmployeeRepository extends JpaRepository<HistoryRecordTableEmployee, Long> {

}
