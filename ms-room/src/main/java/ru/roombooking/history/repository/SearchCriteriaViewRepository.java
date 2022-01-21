package ru.roombooking.history.repository;



import ru.roombooking.history.config.search.specification.SearchCriteria;

import java.util.List;

public interface SearchCriteriaViewRepository<Model> {
    List<Model> search(List<SearchCriteria> params);
    void save(Model entity);
}
