package ru.roombooking.history.config.search;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.roombooking.history.config.search.specification.SearchCriteria;
import ru.roombooking.history.model.RecordTableView;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Data
public class SearchByParams {

    public List<SearchCriteria> getParamsFromSearch (String search) {
        List<SearchCriteria> params = new ArrayList<>();

        Pattern pattern = Pattern.compile("(\\w+?)([:<>])(\\w+?|.*?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
        }
        return params;
    }

    public List<SearchCriteria> getParamsFromRecordTableView(RecordTableView findRecord) {
        List<SearchCriteria> params = new ArrayList<>();

        if (findRecord.getId() != null)
            params.add(new SearchCriteria("id",":",findRecord.getId()));
        if (!findRecord.getEmail().isEmpty())
            params.add(new SearchCriteria("email",":",findRecord.getEmail()));
        if (findRecord.getEmployeeId()!= null)
            params.add(new SearchCriteria("employeeId",":",findRecord.getEmployeeId()));
        if (!findRecord.getEmployeeName().isEmpty())
            params.add(new SearchCriteria("employeeName",":",findRecord.getEmployeeName()));
        if (!findRecord.getEmployeeSurname().isEmpty())
            params.add(new SearchCriteria("employeeSurname",":",findRecord.getEmployeeSurname()));
        if (!findRecord.getEmployeeMiddleName().isEmpty())
            params.add(new SearchCriteria("employeeMiddleName",":",findRecord.getEmployeeMiddleName()));
        if (findRecord.getVcsRoomNumberRoom()!= null)
            params.add(new SearchCriteria("vcsRoomNumberRoom",":",findRecord.getVcsRoomNumberRoom()));
        if (findRecord.getIsActive() != null)
            params.add(new SearchCriteria("isActive",":",findRecord.getIsActive()));
        if (!findRecord.getTitle().isEmpty())
            params.add(new SearchCriteria("title",":",findRecord.getTitle()));
        if (findRecord.getStartEvent() != null)
            params.add(new SearchCriteria("startEvent",":",findRecord.getStartEvent()));
        if (findRecord.getEndEvent() != null)
            params.add(new SearchCriteria("endEvent",":",findRecord.getEndEvent()));

        return params;
    }

}
