package ru.roombooking.departments.repository;



import ru.roombooking.departments.config.search.specification.SearchCriteria;

import java.util.List;

public interface SearchCriteriaViewRepository<Model> {
    List<Model> search(List<SearchCriteria> params);
    void save(Model entity);
}
