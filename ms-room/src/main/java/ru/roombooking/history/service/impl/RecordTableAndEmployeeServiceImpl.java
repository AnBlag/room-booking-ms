package ru.roombooking.history.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.roombooking.history.exception.*;
import ru.roombooking.history.feign.EmployeeFeignClient;
import ru.roombooking.history.feign.SecurityFeignClient;
import ru.roombooking.history.maper.VCMapper;
import ru.roombooking.history.model.RecordTable;
import ru.roombooking.history.model.RecordTableView;
import ru.roombooking.history.model.VscRoom;
import ru.roombooking.history.model.dto.EmployeeDTO;
import ru.roombooking.history.model.dto.RecordTableDTO;
import ru.roombooking.history.repository.RecordTableRepository;
import ru.roombooking.history.repository.RecordTableViewRepository;
import ru.roombooking.history.repository.VscRoomRepository;
import ru.roombooking.history.service.RecordTableAndEmployeeService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecordTableAndEmployeeServiceImpl implements RecordTableAndEmployeeService {
    private final RecordTableRepository recordTableRepository;
    private final RecordTableViewRepository recordTableViewRepository;
    private final EmployeeFeignClient employeeFeignClient;
    private final SecurityFeignClient securityFeignClient;
    private final VscRoomRepository vscRoomRepository;
    private final VCMapper<RecordTable, RecordTableDTO> mapper;

    @Override
    public RecordTableDTO save(RecordTableDTO recordTableDTO, String login) {
        log.info("Сохрание нового бронирования");
        Optional<RecordTable> overlappingRecordTable = recordTableRepository
                .findOverlappingRecordsByStartEventAndEndEvent(recordTableDTO.getStart(), recordTableDTO.getEnd(),
                        getRoomFromRecordTableDTO(recordTableDTO).getId());

        if (overlappingRecordTable.isPresent()) {
            throw new RecordTableBadRequestException("Данное время занято");
        } else {
            log.info("Сохрание нового бронирования успешно завершено");
            return mapper.toDTO(recordTableRepository.save(toRecordTable(recordTableDTO, login)));
        }
    }

    @Override
    public RecordTableDTO delete(RecordTableDTO recordTableDTO, String login) {
        log.info("Удаление бронирования");
        RecordTable recordTable = recordTableRepository.findByLoginAndId(login, recordTableDTO.getId())
                .orElseThrow(() -> new RecordTableBadRequestException("Не найдена запись"));
        recordTableRepository.delete(recordTable);
        log.info("Удаление бронирования успешно завершено");
        return recordTableDTO;
    }

    @Override
    public boolean checkPermissionByUserAndRecordId(String login, Long recordId) {
        log.info("Проверка прав пользователя");

        try {
            try {
                return ((securityFeignClient.isAdmin(login)) |
                        (employeeFeignClient.getProfileById(String.valueOf(recordTableRepository
                                .findById(recordId)
                                .orElseThrow(RecordTableAndEmployeeException::new)
                                .getEmployeeId()))
                                .getLogin().equals(login)));
            } catch (FeignException e) {
                throw new EmployeeRequestException();
            }
        } catch (FeignException e) {
            throw new SecurityRequestException();
        }
    }

    public List<RecordTableView> findAll() {
        log.info("Поиск всех бронирований");
        return recordTableViewRepository.findAll();
    }

    private RecordTable toRecordTable(RecordTableDTO recordTableDTO, String login) {

        try {
            EmployeeDTO employee = employeeFeignClient.findByLogin(login);
            RecordTable recordTable = mapper.toModel(recordTableDTO);
            recordTable.setEmail(employee.getEmail());
            recordTable.setIsActive(true);
            recordTable.setEmployeeId(employee.getId());
            recordTable.setNumberRoomId(getRoomFromRecordTableDTO(recordTableDTO).getId());
            return recordTable;
        } catch (FeignException e) {
            throw new EmployeeRequestException();
        }
    }

    private VscRoom getRoomFromRecordTableDTO(RecordTableDTO recordTableDTO) {
        return vscRoomRepository.findByNumberRoom(Long.parseLong(recordTableDTO.getRoomId()))
                .orElseThrow(() -> new VscRoomBadRequestException("Не найден id комнаты"));
    }
}