package ru.roombooking.history.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.history.config.search.SearchByParams;
import ru.roombooking.history.model.RecordTableView;
import ru.roombooking.history.repository.SearchCriteriaViewRepository;
import ru.roombooking.history.service.RecordTableAndEmployeeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordTableViewNotificationService {
    private final RecordTableAndEmployeeService recordTableAndEmployeeService;
    private final SearchCriteriaViewRepository<RecordTableView> searchCriteriaViewRepository;
    private final SearchByParams searchByParams;

    @Transactional(readOnly = true)
    public List<RecordTableView> findAll() {
        return recordTableAndEmployeeService.findAll();
    }

    @Transactional(readOnly = true)
    public List<RecordTableView> getRecordTableViewListByURLParams(String search) {
        return searchCriteriaViewRepository.search(searchByParams.getParamsFromSearch(search));
    }

    @Transactional(readOnly = true)
    public List<RecordTableView> getRecordTableViewListByRecordTableViewParams(RecordTableView recordTableView) {
        return searchCriteriaViewRepository.search(searchByParams.getParamsFromRecordTableView(recordTableView));
    }
}