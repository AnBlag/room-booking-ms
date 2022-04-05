package ru.roombooking.departments.config.search;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.roombooking.departments.config.search.specification.SearchCriteria;
import ru.roombooking.departments.model.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Data
public class SearchByParams {

    public List<SearchCriteria> getParamsFromSearch(String search) {
        List<SearchCriteria> params = new ArrayList<>();

        Pattern pattern = Pattern.compile("(\\w+?)([:<>])(\\w+?|.*?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
        }
        return params;
    }

    public List<SearchCriteria> getParamsFromDepartment(Department findDepartment) {
        List<SearchCriteria> params = new ArrayList<>();

        if (findDepartment.getId() != null)
            params.add(new SearchCriteria("id", ":", findDepartment.getId()));
        if (findDepartment.getNameDepartment() != null)
            params.add(new SearchCriteria("nameDepartment", ":", findDepartment.getNameDepartment()));
        if (findDepartment.getPosition() != null)
            params.add(new SearchCriteria("position", ":", findDepartment.getPosition()));
        return params;
    }
}