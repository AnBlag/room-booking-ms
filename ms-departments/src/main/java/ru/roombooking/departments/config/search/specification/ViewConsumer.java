package ru.roombooking.departments.config.search.specification;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.function.Consumer;

@Data
@NoArgsConstructor
public class ViewConsumer implements Consumer<SearchCriteria> {
    private Predicate predicate;
    private CriteriaBuilder builder;
    private Root r;

    public ViewConsumer(Predicate predicate, CriteriaBuilder builder, Root r) {
        this.predicate = predicate;
        this.builder = builder;
        this.r = r;
    }

    @Override
    public void accept(SearchCriteria param) {
        if (param.getOperation().equalsIgnoreCase(">")) {
            predicate = builder.and(predicate, builder.greaterThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
        } else if (param.getOperation().equalsIgnoreCase("<")) {
            predicate = builder.and(predicate, builder.lessThan(r.get(param.getKey()), param.getValue().toString()));
        } else if (param.getOperation().equalsIgnoreCase(":")) {
            if (r.get(param.getKey()).getJavaType() == String.class) {
                predicate = builder.and(predicate, builder.like(builder.upper(r.get(param.getKey())),
                        "%" + param.getValue().toString().toUpperCase() + "%"));
            } else {
                predicate = builder.and(predicate, builder.equal(r.get(param.getKey()), param.getValue()));
            }
        }
    }
}
