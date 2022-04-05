package ru.roombooking.history.service;

import ru.roombooking.history.model.RecordTableView;
import ru.roombooking.history.model.dto.RecordTableDTO;

import java.util.List;

public interface RecordTableAndEmployeeService {
    RecordTableDTO save(RecordTableDTO recordTableDTO, String login);

    RecordTableDTO delete(RecordTableDTO recordTableDTO, String login);

    boolean checkPermissionByUserAndRecordId(String login, Long recordId);

    List<RecordTableView> findAll();
}