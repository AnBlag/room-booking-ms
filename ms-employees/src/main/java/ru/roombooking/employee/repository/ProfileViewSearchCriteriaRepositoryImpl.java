package ru.roombooking.employee.repository;

import org.springframework.stereotype.Repository;
import ru.roombooking.employee.config.search.specification.SearchCriteria;
import ru.roombooking.employee.config.search.specification.ViewConsumer;
import ru.roombooking.employee.model.EmployeeView;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ProfileViewSearchCriteriaRepositoryImpl implements SearchCriteriaViewRepository<EmployeeView> {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<EmployeeView> search(List<SearchCriteria> params) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<EmployeeView> query = builder.createQuery(EmployeeView.class);
        final Root r = query.from(EmployeeView.class);

        Predicate predicate = builder.conjunction();
        ViewConsumer searchConsumer = new ViewConsumer(predicate, builder, r);
        params.forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void save(EmployeeView entity) {
        entityManager.persist(entity);
    }

}
