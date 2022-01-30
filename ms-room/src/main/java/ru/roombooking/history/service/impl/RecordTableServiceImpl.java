package ru.roombooking.history.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.history.exception.EmployeeRequestException;
import ru.roombooking.history.exception.RecordTableBadRequestException;
import ru.roombooking.history.feign.EmployeeFeignClient;
import ru.roombooking.history.maper.VCMapper;
import ru.roombooking.history.model.RecordTable;
import ru.roombooking.history.model.RecordTableView;
import ru.roombooking.history.model.dto.EmployeeDTO;
import ru.roombooking.history.model.dto.RecordTableDTO;
import ru.roombooking.history.repository.RecordTableRepository;
import ru.roombooking.history.repository.RecordTableViewRepository;
import ru.roombooking.history.service.RecordTableService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecordTableServiceImpl implements RecordTableService {
    private final RecordTableRepository recordTableRepository;
    private final RecordTableViewRepository recordTableViewRepository;
    private final VCMapper<RecordTable, RecordTableDTO> mapper;
    private final VCMapper<RecordTableView, RecordTableDTO> mapperView;
    private final JdbcTemplate jdbcTemplate;
    private final EmployeeFeignClient employeeFeignClient;
    @Value("${sql.query.batch-update.record-table}")
    private String batchUpdateRecordTable;

    @Override
    public RecordTableDTO save(RecordTableDTO model) {
        return mapper.toDTO(recordTableRepository.save(toRecordTable(model)));
    }

    @Override
    public RecordTableDTO update(RecordTableDTO model, Long aLong) {
        model.setId(aLong);

        RecordTable recordTable = recordTableRepository.findById(aLong)
                .orElseThrow(() -> new RecordTableBadRequestException("Не найдена запись"));

        recordTable.setTitle(model.getTitle());
        recordTable.setEmail(model.getEmail());
        recordTable.setIsActive(model.getIsActive());
        recordTable.setStartEvent(model.getStart());
        recordTable.setEndEvent(model.getEnd());
        recordTableRepository.save(recordTable);

        return mapper.toDTO(recordTable);
    }

    @Override
    public List<RecordTableDTO> findAll() {
        return recordTableRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RecordTableDTO deleteById(Long aLong) {
        RecordTableDTO recordTableDTO = mapper.toDTO(recordTableRepository.findById(aLong)
                .orElseThrow(() -> new RecordTableBadRequestException("Не найден ID")));
        recordTableRepository.deleteById(aLong);
        return recordTableDTO;
    }

    private RecordTable toRecordTable(RecordTableDTO model) {
        RecordTable recordTable = mapper.toModel(model);
        RecordTable temp = recordTableRepository.findByNumberRoomIdAndEmployeeId(recordTable.getNumberRoomId(),
                recordTable.getEmployeeId()).orElseThrow(() -> new RecordTableBadRequestException("Не найден"));
        recordTable.setNumberRoomId(temp.getNumberRoomId());
        recordTable.setEmployeeId(temp.getEmployeeId());
        return recordTable;
    }

    @Override
    public List<RecordTableDTO> findAllByEmployeeFullNameAndRecordAndIsActiveAndNumberRoom() {
        return recordTableViewRepository.findAll()
                .stream()
                .map(mapperView::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RecordTableDTO save(RecordTableDTO recordTableDTO, String login) {
        Optional<RecordTable> recordTable = recordTableRepository.findByLogin(login);
        if (recordTable.isPresent()) {

            try {
                EmployeeDTO employeeDTO = employeeFeignClient
                        .findById(String.valueOf(recordTable.get().getEmployeeId()));
                recordTableDTO.setEmail(employeeDTO.getEmail());
                recordTableDTO.setIsActive(employeeDTO.getIsActive());
            } catch (FeignException e) {
                throw new EmployeeRequestException();
            }
            RecordTable recordTable1 = mapper.toModel(recordTableDTO);
            recordTable1.setEmployeeId(recordTable.get().getEmployeeId());
            recordTable1.setNumberRoomId(recordTable.get().getNumberRoomId());
            return mapper.toDTO(recordTableRepository.save(recordTable1));
        } else {
            throw new RecordTableBadRequestException();
        }
    }

    @Override
    public RecordTableDTO delete(RecordTableDTO recordTableDTO) {
        RecordTable recordTable = recordTableRepository.findByStartEventAndEndEvent(
                recordTableDTO.getStart(), recordTableDTO.getEnd()
        ).orElseThrow(() -> new RecordTableBadRequestException("Не найдена запись"));
        recordTableRepository.delete(recordTable);
        return recordTableDTO;
    }

    @Override
    public List<RecordTableDTO> findByNumberRoom(Long id) {
        return recordTableRepository.findAllByNumberRoom(id)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RecordTableDTO findById(Long id) {
        return mapper.toDTO(recordTableRepository.findById(id).orElseThrow(() -> new RecordTableBadRequestException("Не найдена запись")));
    }

    @Transactional
    @Override
    public void batchUpdateRecords(List<RecordTableDTO> recordTableList) {
        jdbcTemplate.batchUpdate(batchUpdateRecordTable,
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