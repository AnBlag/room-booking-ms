package ru.roombooking.departments.repository;

import org.springframework.stereotype.Repository;
import ru.roombooking.departments.config.search.specification.SearchCriteria;
import ru.roombooking.departments.config.search.specification.ViewConsumer;
import ru.roombooking.departments.model.Department;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DepartmentSearchCriteriaRepositoryImpl implements SearchCriteriaViewRepository<Department> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Department> search(List<SearchCriteria> params) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Department> query = builder.createQuery(Department.class);
        final Root r = query.from(Department.class);

        Predicate predicate = builder.conjunction();
        ViewConsumer searchConsumer = new ViewConsumer(predicate, builder, r);
        params.forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void save(Department entity) {
        entityManager.persist(entity);
    }
}
