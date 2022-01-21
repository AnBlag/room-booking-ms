package ru.roombooking.employee.config.search;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.roombooking.employee.config.search.specification.SearchCriteria;
import ru.roombooking.employee.model.EmployeeView;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Data
public class SearchByURLParams {

    public List<SearchCriteria> getParamsFromSearch (String search) {
        List<SearchCriteria> params = new ArrayList<>();

        Pattern pattern = Pattern.compile("(\\w+?)([:<>])(\\w+?|.*?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
        }
        return params;
    }

    public List<SearchCriteria> getParamsFromProfileView (EmployeeView employeeView) {
        List<SearchCriteria> params = new ArrayList<>();

        if (employeeView.getId() != null)
            params.add(new SearchCriteria("id",":", employeeView.getId()));
        if (employeeView.getName() != null)
            params.add(new SearchCriteria("name",":", employeeView.getName()));
        if (employeeView.getSurname() != null)
            params.add(new SearchCriteria("surname",":", employeeView.getSurname()));
        if (employeeView.getMiddleName() != null)
            params.add(new SearchCriteria("middleName",":", employeeView.getMiddleName()));
        if (employeeView.getPhone() != null)
            params.add(new SearchCriteria("phone",":", employeeView.getPhone()));
        if (employeeView.getEmail() != null)
            params.add(new SearchCriteria("email",":", employeeView.getEmail()));
        if (employeeView.getBanned() != null)
            params.add(new SearchCriteria("banned",":", employeeView.getBanned()));

        return params;
    }
}
