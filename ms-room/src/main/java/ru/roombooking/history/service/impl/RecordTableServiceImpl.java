package ru.roombooking.history.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.history.exception.RecordTableBadRequestException;
import ru.roombooking.history.exception.RecordTableDeleteException;
import ru.roombooking.history.exception.RecordTableSaveException;
import ru.roombooking.history.exception.RecordTableUpdateException;
import ru.roombooking.history.maper.VCMapper;
import ru.roombooking.history.model.RecordTable;
import ru.roombooking.history.model.RecordTableView;
import ru.roombooking.history.model.dto.RecordTableDTO;
import ru.roombooking.history.repository.RecordTableRepository;
import ru.roombooking.history.repository.RecordTableViewRepository;
import ru.roombooking.history.service.RecordTableService;
import static ru.roombooking.history.exception.ExceptionMessage.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class RecordTableServiceImpl implements RecordTableService {
    private final RecordTableRepository recordTableRepository;
    private final RecordTableViewRepository recordTableViewRepository;
    private final VCMapper<RecordTable, RecordTableDTO> mapper;
    private final VCMapper<RecordTableView, RecordTableDTO> mapperView;
    private final JdbcTemplate jdbcTemplate;
    private String SQL_BATCH_UPDATE_RECORD_TABLE =
            "update record_table set employee_id=?, number_room_id=?, is_active=?, email=?, title=?, start_event=?, end_event=? where id=?";

    @Override
    @Transactional(rollbackFor = RecordTableSaveException.class)
    public RecordTableDTO save(RecordTableDTO model) {
        log.info("Сохранение бронирования");
        return mapper.toDTO(recordTableRepository.save(mapper.toModel(model)));
    }

    @Override
    @Transactional(rollbackFor = RecordTableUpdateException.class)
    public RecordTableDTO update(RecordTableDTO model, Long aLong) {
        log.info("Обновление бронирования");
        model.setId(aLong);

        RecordTable recordTable = recordTableRepository.findById(aLong)
                .orElseThrow(() -> new RecordTableBadRequestException(RECORD_NOT_FOUND.getMessage()));

        recordTable.setTitle(model.getTitle());
        recordTable.setEmail(model.getEmail());
        recordTable.setIsActive(model.getIsActive());
        recordTable.setStartEvent(model.getStart());
        recordTable.setEndEvent(model.getEnd());
        recordTableRepository.save(recordTable);
        log.info("Обновление бронирования успешно завершено");

        return mapper.toDTO(recordTable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecordTableDTO> findAll() {
        log.info("Поиск всех бронирований");
        return recordTableRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = RecordTableDeleteException.class)
    public RecordTableDTO deleteById(Long aLong) {
        log.info("Удаление бронирования по ID");
        RecordTableDTO recordTableDTO = mapper.toDTO(recordTableRepository.findById(aLong)
                .orElseThrow(() -> new RecordTableBadRequestException(ID_NOT_FOUND.getMessage())));
        recordTableRepository.deleteById(aLong);
        log.info("Удаление бронирования по ID успешно завершено");
        return recordTableDTO;
    }

    /*private RecordTable toRecordTable(RecordTableDTO model) {
        RecordTable recordTable = mapper.toModel(model);
        RecordTable temp = recordTableRepository.findByNumberRoomIdAndEmployeeId(recordTable.getNumberRoomId(),
                recordTable.getEmployeeId()).orElseThrow(() ->
                new RecordTableBadRequestException(RECORD_NOT_FOUND.getMessage()));
        recordTable.setNumberRoomId(temp.getNumberRoomId());
        recordTable.setEmployeeId(temp.getEmployeeId());
        return recordTable;
    }*/

    @Override
    public List<RecordTableDTO> findAllByEmployeeFullNameAndRecordAndIsActiveAndNumberRoom() {
        log.info("Поиск бронирования по ФИО, дате, активности и номеру комнаты");
        return recordTableViewRepository.findAll()
                .stream()
                .map(mapperView::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RecordTableDTO delete(RecordTableDTO recordTableDTO) {
        log.info("Удаление бронирования");
        RecordTable recordTable = recordTableRepository.findByStartEventAndEndEvent(
                recordTableDTO.getStart(), recordTableDTO.getEnd()
        ).orElseThrow(() -> new RecordTableBadRequestException(RECORD_NOT_FOUND.getMessage()));
        recordTableRepository.delete(recordTable);
        log.info("Удаление бронирования успешно завершено");
        return recordTableDTO;
    }

    @Override
    public List<RecordTableDTO> findByNumberRoom(Long id) {
        log.info("Поиск бронирования по номеру комнаты");
        return recordTableRepository.findAllByNumberRoom(id)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RecordTableDTO findById(Long id) {
        log.info("Поиск бронирования по ID");
        return mapper.toDTO(recordTableRepository.findById(id)
                .orElseThrow(() -> new RecordTableBadRequestException(RECORD_NOT_FOUND.getMessage())));
    }

    @Transactional
    @Override
    public void batchUpdateRecords(List<RecordTableDTO> recordTableList) {
        log.info("Обновление всех бронирований");
        jdbcTemplate.batchUpdate(SQL_BATCH_UPDATE_RECORD_TABLE,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, recordTableList.get(i).getEmployeeId());
                        ps.setLong(2, recordTableList.get(i).getNumberRoomId());
                        ps.setBoolean(3, recordTableList.get(i).getIsActive());
                        ps.setString(4, recordTableList.get(i).getEmail());
                        ps.setString(5, recordTableList.get(i).getTitle());
                        ps.setTimestamp(6, Timestamp.from(recordTableList.get(i).getStart().toInstant()));
                        ps.setTimestamp(7, Timestamp.from(recordTableList.get(i).getEnd().toInstant()));
                        ps.setLong(8, recordTableList.get(i).getId());
                    }

                    @Override
                    public int getBatchSize() {
                        return recordTableList.size();
                    }
                });
    }
}