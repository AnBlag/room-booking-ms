package ru.roombooking.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.roombooking.admin.model.Department;
import ru.roombooking.admin.model.Profile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = "true")
public class EmployeeEditRequest {

    private EmployeeDTO employeeDTO;
    private Profile profile;
    private List<Department> departmentList;

}
