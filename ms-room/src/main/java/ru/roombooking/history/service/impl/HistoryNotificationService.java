package ru.roombooking.history.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.history.model.dto.RecordTableDTO;
import ru.roombooking.history.service.HistoryRecordTableEmployeeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryNotificationService {
    private final HistoryRecordTableEmployeeService historyRecordTableEmployeeService;

    @Transactional(readOnly = true)
    public List<RecordTableDTO> findAll() {
        return historyRecordTableEmployeeService.findAll();
    }

    public RecordTableDTO deleteById(Long id) {
        return historyRecordTableEmployeeService.deleteById(id);
    }
}