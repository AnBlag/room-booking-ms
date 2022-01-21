package ru.roombooking.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.roombooking.admin.model.Profile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = "true")
public class EmployeeSaveRequest {
    private String id;
    private EmployeeDTO employeeDTO;
    private Profile profile;



}
