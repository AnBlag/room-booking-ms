package ru.roombooking.history.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.history.model.dto.RecordTableDTO;
import ru.roombooking.history.service.HistoryRecordTableEmployeeAndRecordTableService;
import ru.roombooking.history.service.HistoryRecordTableEmployeeService;
import ru.roombooking.history.service.RecordTableAndEmployeeService;
import ru.roombooking.history.service.RecordTableService;

@Service
@RequiredArgsConstructor
@Slf4j
public class HistoryRecordTableEmployeeAndRecordTableServiceImpl implements HistoryRecordTableEmployeeAndRecordTableService<RecordTableDTO, String, Long> {
    private final HistoryRecordTableEmployeeService historyRecordTableEmployeeService;
    private final RecordTableAndEmployeeService recordTableAndEmployeeService;
    private final RecordTableService recordTableService;

    @Transactional
    public RecordTableDTO save(RecordTableDTO model, String login) {
        log.info("Сохрание бронирования");
        RecordTableDTO recordTableDTO = recordTableAndEmployeeService.save(model, login);
        historyRecordTableEmployeeService.save(recordTableDTO);
        log.info("Сохрание бронирования успешно завершено");
        return recordTableDTO;
    }

    @Transactional
    public RecordTableDTO update(RecordTableDTO model, Long aLong) {
        log.info("Обновление бронирования");
        model.setId(aLong);
        recordTableService.update(model, aLong);
        historyRecordTableEmployeeService.save(model);
        log.info("Обновление бронирования успешно завершено");
        return model;
    }
}