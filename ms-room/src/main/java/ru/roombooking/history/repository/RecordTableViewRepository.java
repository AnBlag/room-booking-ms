package ru.roombooking.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.roombooking.history.model.RecordTableView;

@Repository
public interface RecordTableViewRepository extends JpaRepository<RecordTableView, Long> {
}