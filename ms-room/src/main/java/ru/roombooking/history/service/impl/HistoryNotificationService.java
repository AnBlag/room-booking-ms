package ru.roombooking.history.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.history.config.search.SearchByParams;
import ru.roombooking.history.model.HistoryRecordTableEmployee;
import ru.roombooking.history.model.RecordTableView;
import ru.roombooking.history.model.dto.RecordTableDTO;
import ru.roombooking.history.repository.SearchCriteriaViewRepository;
import ru.roombooking.history.service.HistoryRecordTableEmployeeService;
import ru.roombooking.history.service.RecordTableAndEmployeeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryNotificationService {
    private final HistoryRecordTableEmployeeService historyRecordTableEmployeeService;

    @Transactional(readOnly = true)
    public List<RecordTableDTO> findAll() {
        return historyRecordTableEmployeeService.findAll();
    }
}