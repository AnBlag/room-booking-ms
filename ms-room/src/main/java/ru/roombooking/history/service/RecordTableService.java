package ru.roombooking.history.service;

import ru.roombooking.history.model.dto.RecordTableDTO;

import java.util.List;

public interface RecordTableService extends RoomServiceCRUD<RecordTableDTO, Long> {
    List<RecordTableDTO> findAllByEmployeeFullNameAndRecordAndIsActiveAndNumberRoom();

    RecordTableDTO save(RecordTableDTO recordTableDTO, String login);

    RecordTableDTO delete(RecordTableDTO recordTableDTO);

    RecordTableDTO findById(Long id);

    List<RecordTableDTO> findByNumberRoom(Long id);

    void batchUpdateRecords(List<RecordTableDTO> recordTableList);
}