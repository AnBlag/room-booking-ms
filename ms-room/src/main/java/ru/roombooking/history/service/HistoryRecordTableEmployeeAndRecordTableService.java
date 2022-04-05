package ru.roombooking.history.service;

import ru.roombooking.history.model.dto.RecordTableDTO;

public interface HistoryRecordTableEmployeeAndRecordTableService<Model, String, Long> {

    RecordTableDTO save(Model model, String login);

    RecordTableDTO update(Model model, Long aLong);
}