package ru.roombooking.history.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import static ru.roombooking.history.exception.ExceptionMessage.*;
import ru.roombooking.history.exception.RecordTableBadRequestException;
import ru.roombooking.history.maper.VCMapper;
import ru.roombooking.history.model.HistoryRecordTableEmployee;
import ru.roombooking.history.model.dto.RecordTableDTO;
import ru.roombooking.history.repository.HistoryRecordTableEmployeeRepository;
import ru.roombooking.history.service.HistoryRecordTableEmployeeService;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class HistoryRecordTableEmployeeServiceImpl implements HistoryRecordTableEmployeeService {
    private final HistoryRecordTableEmployeeRepository recordTableRepository;
    private final VCMapper<HistoryRecordTableEmployee, RecordTableDTO> mapper;

    @Override
    public RecordTableDTO save(RecordTableDTO model) {
        log.info("Сохрание бронирования в историю");
        return mapper.toDTO(recordTableRepository.save(mapper.toModel(model)));
    }

    @Override
    public RecordTableDTO update(RecordTableDTO model, Long aLong) {
        log.info("Обновление бронирования в истории");
        model.setId(aLong);
        return mapper.toDTO(recordTableRepository.save(mapper.toModel(model)));
    }

    @Override
    public List<RecordTableDTO> findAll() {
        log.info("Поиск всех бронирований в истории");
        return recordTableRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(HistoryRecordTableEmployee::getId))
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RecordTableDTO deleteById(Long aLong) {
        log.info("Удаление бронирования из истории");
        RecordTableDTO recordTableDTO = mapper.toDTO(recordTableRepository.findById(aLong)
                .orElseThrow(() -> new RecordTableBadRequestException(ID_NOT_FOUND.getMessage())));
        recordTableRepository.deleteById(aLong);
        log.info("Удаление бронирования из истории по ID успешно завершено");
        return recordTableDTO;
    }
}