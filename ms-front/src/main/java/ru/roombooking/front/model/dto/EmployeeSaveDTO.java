package ru.roombooking.front.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = "true")
public class EmployeeSaveDTO {
    private String id;
    private EmployeeDTO employeeDTO;
    private ProfileDTO profile;
}